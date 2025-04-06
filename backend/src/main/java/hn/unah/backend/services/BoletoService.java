package hn.unah.backend.services;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hn.unah.backend.models.Boleto;
import hn.unah.backend.models.EstadoBoleto;
import hn.unah.backend.models.Tarjeta;
import hn.unah.backend.models.Usuario;
import hn.unah.backend.repository.BoletoRepository;
import hn.unah.backend.repository.EstadoBoletoRepository;
import hn.unah.backend.repository.TarjetaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class BoletoService {

    @Autowired
    private BoletoRepository boletoRepository;

    @Autowired
    private TarjetaRepository tarjetaRepository;

    @Autowired
    private EstadoBoletoRepository estadoBoletoRepository;

    @Transactional
    public String pagarBoleto(int codigoBoleto) {
        try {
            // Buscar el boleto
            Boleto boleto = boletoRepository.findById(codigoBoleto)
                    .orElseThrow(() -> new EntityNotFoundException("No se encontró el boleto con código " + codigoBoleto));
    
            // Verificar si ya está pagado
            if (boleto.getEstado().getNombre().equalsIgnoreCase("Pagado")) {
                return "El boleto ya está pagado.";
            }
    
            // Obtener el usuario
            Usuario usuario = boleto.getUsuario();
            if (usuario == null) {
                throw new IllegalArgumentException("El boleto no está asociado a ningún usuario.");
            }
    
            // Obtener la tarjeta más reciente o prioritaria
            Tarjeta tarjeta = tarjetaRepository.findTopByUsuarioOrderByFechaEmisionDesc(usuario)
                    .orElseThrow(() -> new EntityNotFoundException("El usuario no tiene una tarjeta registrada."));
    
            BigDecimal precio = boleto.getPrecioTotal();
            BigDecimal saldo = tarjeta.getSaldo();
    
            // Validar saldo
            if (saldo.compareTo(precio) < 0) {
                return "Saldo insuficiente en la tarjeta.";
            }
    
            // Realizar el pago
            tarjeta.setSaldo(saldo.subtract(precio));
    
            // Cambiar estado del boleto a "Pagado"
            EstadoBoleto estadoPagado = estadoBoletoRepository.findByNombre("Pagado")
                    .orElseThrow(() -> new EntityNotFoundException("Estado 'Pagado' no encontrado."));
    
            boleto.setEstado(estadoPagado);
    
            // Guardar cambios
            tarjetaRepository.save(tarjeta);
            boletoRepository.save(boleto);
    
            return "Pago realizado con éxito.";
        } catch (EntityNotFoundException ex) {
            return "Error: " + ex.getMessage();
        } catch (IllegalArgumentException ex) {
            return "Error en la lógica de negocio: " + ex.getMessage();
        } catch (Exception ex) {
            // Captura cualquier error no controlado
            return "Ocurrió un error inesperado: " + ex.getMessage();
        }
    }


}

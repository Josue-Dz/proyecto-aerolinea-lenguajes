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

            Boleto boleto = this.boletoRepository.findById(codigoBoleto)
                            .orElseThrow(() -> new EntityNotFoundException("No se encontró el boleto con código " + codigoBoleto));

            
    
            
            if (boleto.getEstado().getNombre().equalsIgnoreCase("Pagado")) {
                return "El boleto ya está pagado.";
            }
    
            Usuario usuario = boleto.getUsuario();
            if (usuario == null) {
                throw new IllegalArgumentException("El boleto no está asociado a ningún usuario.");
            }
    
            // Obtiene la tarjeta más reciente o prioritaria y validar saldo
            Tarjeta tarjeta = tarjetaRepository.findTopByUsuarioOrderByFechaEmisionDesc(usuario)
                    .orElseThrow(() -> new EntityNotFoundException("El usuario no tiene una tarjeta registrada."));
    
            BigDecimal precio = boleto.getPrecioTotal();
            BigDecimal saldo = tarjeta.getSaldo();
    
            
            if (saldo.compareTo(precio) < 0) {
                return "Saldo insuficiente en la tarjeta.";
            }
    
            // Realizar el pago y cambia el estado del boleto de pendiente a pagado
            tarjeta.setSaldo(saldo.subtract(precio));
            EstadoBoleto estadoPagado = estadoBoletoRepository.findByNombre("Pagado").get();
            boleto.setEstado(estadoPagado);
    
            // Guarda los cambios en la bd
            tarjetaRepository.save(tarjeta);
            boletoRepository.save(boleto);
    
            return "Pago realizado con éxito.";
        } catch (EntityNotFoundException ex) {
            return "Error: " + ex.getMessage();
        } catch (IllegalArgumentException ex) {
            return "Error en la lógica de negocio: " + ex.getMessage();
        } catch (Exception ex) {
            return "Ocurrió un error inesperado: " + ex.getMessage();
        }
    }


}

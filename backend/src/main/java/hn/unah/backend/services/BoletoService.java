package hn.unah.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hn.unah.backend.models.Boleto;
import hn.unah.backend.models.Tarjeta;
import hn.unah.backend.models.Usuario;
import hn.unah.backend.repository.BoletoRepository;
import hn.unah.backend.repository.TarjetaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class BoletoService {

    @Autowired
    private BoletoRepository boletoRepository;

    @Autowired
    private TarjetaRepository tarjetaRepository;

    @Transactional
    public String pagarBoleto(int codigoBoleto) {
        Boleto boleto = boletoRepository.findById(codigoBoleto)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró el boleto con código " + codigoBoleto));

        if (boleto.isPagado()) {
            return "El boleto ya está pagado.";
        }

        Usuario usuario = boleto.getUsuario();
        Tarjeta tarjeta = tarjetaRepository.findTopByUsuario(usuario)
                .orElseThrow(() -> new EntityNotFoundException("El usuario no tiene una tarjeta registrada."));

        int precio = boleto.getPrecioTotal();
        int saldo = tarjeta.getSaldo();

        if (saldo < precio) {
            return "Saldo insuficiente en la tarjeta.";
        }

        // Realizar el pago
        tarjeta.setSaldo(saldo - precio);
        boleto.setPagado(true);
        tarjetaRepository.save(tarjeta);
        boletoRepository.save(boleto);

        return "Pago realizado con éxito.";
    }

}

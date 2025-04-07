<?php

namespace App\Http\Controllers;

use App\Models\Asiento;
use Illuminate\Http\Request;
use GuzzleHttp\Client;
use GuzzleHttp\Exception\RequestException;

class AerolineaController extends Controller
{
    public function aerolinea()
    {
        $cliente = new Client();

        $response = $cliente->get("http://127.0.0.1:8080/api/aeropuerto/aeropuertos");

        $aeropuertos = json_decode($response->getBody());

        if (empty($aeropuertos)) {
            return back()->withErrors(['error' => 'No existen los aeropuertos']);
        }

        return view('inicio', compact('aeropuertos'));
    }

    public function busqueda(Request $request)
    {
        $cliente = new Client();

        $body = [
            'origen' => $request->input('origen'),
            'destino' => $request->input('destino'),
            'fechaSalida' => $request->input('fechaSalida'),
            'fechaRegreso' => $request->input('fechaRegreso'),
            'cantidadViajeros' => (int) $request->input('viajeros')
        ];

        try {
            $response = $cliente->post('http://127.0.0.1:8080/api/reservas/mostrar', [
                'json' => $body
            ]);

            $vuelos = json_decode($response->getBody());

            if (empty($vuelos)) {
                return back()->withErrors(['error' => 'No se encontraron vuelos disponibles.']);
            }

            return view('buscar', compact('vuelos'));
        } catch (RequestException $e) {
            return back()->withErrors(['error' => 'Hubo un error al consultar los vuelos.']);
        }
    }

    public function reservar($idVuelo)
    {
        $cliente = new Client();

        $response = $cliente->get("http://localhost:8080/api/reservas/reservar/$idVuelo");

        $data = json_decode($response->getBody());

        $asientos = $data->asientos;
        $vuelo = $data->vuelo;

        if (empty($asientos)) {
            return back()->withErrors(['error' => 'No hay asientos disponibles para este vuelo']);
        }

        return view('reservar', compact('asientos', 'vuelo'));
    }

    public function confirmarPago($codigoBoleto)
    {
        $cliente = new Client();

        $response = $cliente->get("http://127.0.0.1:8080/api/boletos/pagar/$codigoBoleto");
        $pago = json_decode($response->getBody());

        return view('inicio', compact('pago')); //Cambiar para que retorne vista con Pago exitoso
    }

    public function verHistorial($idUsuario)
    {
        $cliente = new Client();

        $response = $cliente->get("http://127.0.0.1:8080/api/vuelo/historial/$idUsuario");
        $data = json_decode($response->getBody());
        $vuelos = $data->vuelosFuturos;

        if (!$vuelos) {
            return back()->withErrors(['error' => "Este Usuario no tiene historial de vuelos"]);
        }

        return view('historial', compact('vuelos'));
    }
}

<?php

namespace App\Http\Controllers;

use App\Models\Asiento;
use Illuminate\Http\Request;
use GuzzleHttp\Client;
use GuzzleHttp\Exception\RequestException;

class BusquedaController extends Controller
{
    public function buscar(Request $request)
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
            $response = $cliente->get('http://127.0.0.1:8080/api/reservas/mostrar', [
                'json' => $body
            ]);

            $reserva = json_decode($response->getBody());

            return view('buscar', compact('reserva'));
        } catch (RequestException $e) {
            // Puedes redirigir con un error
            return back()->withErrors(['error' => 'No se encontraron reservas disponibles.']);
        }
    }
}

<?php

namespace App\Http\Controllers;

use App\Models\Asiento;
use Illuminate\Http\Request;
use GuzzleHttp\Client;
use GuzzleHttp\Exception\RequestException;

class ReservaController extends Controller
{

    public function obtenerAsientos($idVuelo){
        $cliente = new Client();

        $headers = [
            'Content-Type' => 'application/json'
        ];

        $resultado = $cliente->get("http://localhost:8080/api/reserva/reservar/{idVuelo}", [
            'headers' => $headers,
        ]);

        $asientos = json_decode($resultado->getBody());

        return $asientos;
    }


    public function mostrarVuelos(Request $request){

        $cliente = new Client();

        try{
            $response = $cliente->get('http://localhost:8080/api/reservas/mostrar',[
                'headers' => [
                    'Content-Type' => 'application/json',
                    'Accept' => 'application/jso',
                ],
                'body' => json_encode([
                    ''
                ])

            ]);
        }catch(\GuzzleHttp\Exception\ClientException $e){
            return back()->withErrors(['vuelos'=>'Error al mostrar los vuelos']);
        }
    }

    public function mostrarVuelo($idVuelo)
    {
        $cliente = new Client();

        $resultado = $cliente->get("http://localhost:8080/api/aerolinea/reserva/reservar/$idVuelo");

        $data = json_decode($resultado->getBody(), true); // trae asientos y vuelo

        return view('reservar', [
            'asientos' => $data['asientos'],
            'vuelo' => $data['vuelo']
        ]);
    }
}

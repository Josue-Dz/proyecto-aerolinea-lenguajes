<?php

namespace App\Http\Controllers;

use App\Models\Asiento;
use Illuminate\Http\Request;
use GuzzleHttp\Client;
use GuzzleHttp\Exception\RequestException;

class ReservaController extends Controller
{

    public function obtenerAsientos($idVuelo)
    {
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
}

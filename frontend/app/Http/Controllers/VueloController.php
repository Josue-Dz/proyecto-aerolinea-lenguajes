<?php

namespace App\Http\Controllers;

use App\Models\Asiento;
use Illuminate\Http\Request;
use GuzzleHttp\Client;
use GuzzleHttp\Exception\RequestException;

class VueloController extends Controller
{

    public function verHistorial($idUsuario)
    {
        $cliente = new Client();

        $headers = [
            'Content-Type' => 'application/json'
        ];

        $resultado = $cliente->get("http://127.0.0.1:8080/api/vuelo/historial/{idUsuario}", [
            'headers' => $headers,
        ]);

        $vuelos = json_decode($resultado->getBody());

        return $vuelos;
    }
}

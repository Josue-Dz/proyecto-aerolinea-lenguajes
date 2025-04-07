<?php

namespace App\Http\Controllers;

use App\Models\Asiento;
use Illuminate\Http\Request;
use GuzzleHttp\Client;
use GuzzleHttp\Exception\RequestException;

class AeropuertoController extends Controller
{

    public function mostrarAeropuertos()
    {
        try {
            $cliente = new Client();

            $response = $cliente->get("http://localhost:8080/api/aerolinea/aeropuertos");

            $aeropuertos = json_decode($response->getBody(), true);

            return view('inicio', [
                'aeropuertos' => $aeropuertos
            ]);
        } catch (RequestException $e) {
            return back()->withErrors(['error' => 'No se pudieron obtener los aeropuertos.']);
        }
    }
}

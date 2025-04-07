<?php

namespace App\Http\Controllers;

use App\Models\Asiento;
use Illuminate\Http\Request;
use GuzzleHttp\Client;
use GuzzleHttp\Exception\RequestException;

class TarjetaController extends Controller
{
    public function guardarTarjeta(Request $request)
    {
        $cliente = new Client();

        $headers = [
            'Content-Type' => 'application/json',
        ];

        $body = [
            'numero' => $request->input('numero'),
            'nombre' => $request->input('nombre'),
            'cvv' => $request->input('cvv'),
            'fechaEmision' => $request->input('fechaEmision'),
            'fechaVencimiento' => $request->input('fechaVencimiento'),
            'saldo' => $request->input('saldo')
        ];

        try {
            $response = $cliente->post('http://localhost:8080/api/tarjeta/guardar', [
                'headers' => $headers,
                'json' => $body,
            ]);

            $tarjeta = json_decode($response->getBody(), true);

            return redirect()->back()->with('success', $tarjeta['mensaje'] ?? 'Tarjeta guardada.');
        } catch (\GuzzleHttp\Exception\ClientException $e) {
            // Error como 409 (tarjeta duplicada)
            $responseBody = json_decode($e->getResponse()->getBody()->getContents(), true);
            return redirect()->back()->with('error', $responseBody['mensaje'] ?? 'Error al guardar tarjeta.');
        } catch (\Exception $e) {
            // Error 500 u otros
            return redirect()->back()->with('error', 'Error al conectar con el servidor.');
        }
    }
    public function pagarBoleto($idBoleto)
    {
        $cliente = new Client();

        $headers = [
            'Content-Type' => 'application/json'
        ];

        $resultado = $cliente->get("http://localhost:8080/api/boletos/pagar/{codigoBoleto}", [
            'headers' => $headers,
        ]);

        $pago = json_decode($resultado->getBody());

        return $pago;
    }
}

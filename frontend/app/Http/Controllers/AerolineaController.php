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
        return view('inicio');
    }

    public function busqueda(Request $request)
    {
        return view('buscar');
    }


    public function reservar(Request $request, $idVuelo)
    {
        return view('reservar', compact('asientos'));
    }

    public function pagar(Request $request)
    {
        return view('pagar');
    }
}

<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Auth;
use GuzzleHttp\Client;
use GuzzleHttp\Exception\RequestException;

class AuthController extends Controller
{
    // Muestra el formulario de inicio de sesión
    public function mostrarFormulario()
    {
        return view('iniciar-sesion');
    }

    public function mostrarFormularioRegistro()
    {
        return view('registrar');
    }

    public function iniciarSesion(Request $request)
    {
        $client = new Client();

        try {
            $response = $client->post('http://localhost:8080/api/auth/acceso', [
                'headers' => [
                    'Content-Type' => 'application/json',
                    'Accept' => 'application/json',
                ],
                'body' => json_encode([
                    'username' => $request->input('correo'),
                    'password' => $request->input('password'),
                ]),
            ]);


            $body = json_decode($response->getBody(), true);
            $jwtToken = $body['jwt'];


            // Guarda el token JWT en la sesión de Laravel
            $request->session()->put('jwtToken', $jwtToken);
            $request->session()->put('authenticated', $body['authenticated']);

            return redirect()->route('inicio');
        } catch (\GuzzleHttp\Exception\ClientException $e) {
            return back()->withErrors(['login' => 'Credenciales incorrectas.']);
        }
    }

    public function cerrarSesion(Request $request)
    {
        // Elimina el token JWT de la sesión
        $request->session()->forget('jwtToken');
        $request->session()->forget('authenticated');

        return redirect()->route('inicio');
    }

    public function registro(Request $request)
    {
        $cliente = new Client();

        try {
            $response = $cliente->post('http://localhost:8080/api/auth/registro', [
                'headers' => [
                    'Content-Type' => 'application/json',
                    'Accept' => 'application/json',
                ],
                'body' => json_encode([
                    'nombre' => $request->input('nombre'),
                    'apellido' => $request->input('apellido'),
                    'correo' => $request->input('correo'),
                    'contrasenia' => $request->input('contrasenia'),
                    'fechaNacimiento' => $request->input('fechaNacimiento'),
                ]),
            ]);

            // Decodifica la respuesta JSON y obtiene el token JWT
            $body = json_decode($response->getBody(), true);
            $jwtToken = $body['jwt'];

            return response()->json($body);

            // Guarda el token JWT en la sesión de Laravel
            $request->session()->put('jwtToken', $jwtToken);
            $request->session()->put('authenticated', $body['authenticated']);

            return redirect()->route('inicio')->with('success', 'Registro exitoso. Puedes iniciar sesión ahora.');
        } catch (\GuzzleHttp\Exception\ClientException $e) {
            return back()->withErrors(['registro' => 'Error al registrarse']);
        }
    }
}

<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Http;
use Illuminate\Support\Facades\Validator;

class RegistroController extends Controller
{
    public function mostrarFormularioRegistro()
    {
        return view('registrar');
    }

    public function registrar(Request $request)
    {
        // Validación de los datos antes de enviarlos a Spring Boot
        $validator = Validator::make($request->all(), [
            'nombre' => ['required', 'string', 'max:255'],
            'correo' => ['required', 'string', 'email', 'max:255'],
            'contraseña' => ['required', 'string', 'min:8', 'confirmed'], // La confirmación se hace con un campo "contraseña_confirmation"
        ]);

        if ($validator->fails()) {
            return back()->withErrors($validator)->withInput();
        }

        // Enviar datos al backend de Spring Boot
        $respuesta = Http::post('http://localhost:8080/api/registro', [
            'nombre' => $request->input('nombre'),
            'correo' => $request->input('correo'),
            'contraseña' => $request->input('contraseña'),
        ]);

        if ($respuesta->successful()) {
            return redirect()->route('iniciar-sesion')->with('success', 'Registro exitoso, inicia sesión.');
        } else {
            return back()->withErrors(['error' => 'No se pudo registrar.'])->withInput();
        }
    }
}

<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Auth;


class AuthController extends Controller
{
    // Muestra el formulario de inicio de sesión
    public function mostrarFormulario()
    {
        return view('iniciar-sesion');
    }
}

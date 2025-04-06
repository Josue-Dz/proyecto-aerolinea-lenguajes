<?php

use App\Http\Controllers\AerolineaController;
use App\Http\Controllers\AuthController;
use App\Http\Controllers\RegistroController;
use Illuminate\Support\Facades\Route;

Route::get('/', function () {
    return view('welcome');
});

//Para cargar las vistas
Route::get('/inicio',
    [AerolineaController::class, 'aerolinea'])->name('inicio');

Route::get('/buscar',
    [AerolineaController::class, 'busqueda'])->name('buscar');

Route::get('/reservar',
    [AerolineaController::class, 'reservar'])->name('reservar');

Route::get('/pagar',
    [AerolineaController::class, 'pagar'])->name('pagar');


//Para inicio de sesión y registro
Route::get('/iniciar-sesion', 
            [AuthController::class, 'mostrarFormulario'])->name('iniciar-sesion');

Route::post('/iniciarSesion', 
            [AuthController::class, 'iniciarSesion'])->name('iniciar.sesion');

Route::get('/formularioRegistro', 
            [AuthController::class, 'mostrarFormularioRegistro'])->name('registro.formulario');

Route::post('/registrar', 
            [AuthController::class, 'registro'])->name('registro.usuario');

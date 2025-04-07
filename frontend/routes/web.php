<?php

use App\Http\Controllers\AerolineaController;
use App\Http\Controllers\AuthController;
use App\Http\Controllers\TarjetaController;
use Illuminate\Support\Facades\Route;

Route::get('/', function () {
    return view('welcome');
});

//Para cargar las vistas
Route::get('/inicio',
    [AerolineaController::class, 'aerolinea'])->name('inicio');

Route::post('/buscar',
    [AerolineaController::class, 'busqueda'])->name('buscar');

Route::get('/reservar',
    [AerolineaController::class, 'reservar'])->name('reservar');

// Ruta para guardar tarjeta y pagar
Route::get('/aerolinea/pagar/{codigoBoleto?}', [AerolineaController::class, 'pagar'])->name('pagar');
Route::post('/aerolinea/pagar', [TarjetaController::class, 'guardarTarjeta'])->name('pagar');

// Rutas para reservar vuelos y ver el historial
Route::get('/aerolinea/reservar/{idVuelo?}', [AerolineaController::class, 'reservar'])->name('reservar');
Route::get('/aerolinea/historial/{idUsuario?}', [AerolineaController::class, 'verHistorial'])->name('historial');

//Para inicio de sesión y registro
Route::get('/iniciar-sesion', 
            [AuthController::class, 'mostrarFormulario'])->name('iniciar-sesion');

Route::post('/iniciarSesion', 
            [AuthController::class, 'iniciarSesion'])->name('iniciar.sesion');

Route::get('/formularioRegistro', 
            [AuthController::class, 'mostrarFormularioRegistro'])->name('registro.formulario');

Route::post('/registrar', 
            [AuthController::class, 'registro'])->name('registro.usuario');

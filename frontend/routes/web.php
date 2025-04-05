<?php

use App\Http\Controllers\AerolineaController;
use App\Http\Controllers\AuthController;
use App\Http\Controllers\RegistroController;
use Illuminate\Support\Facades\Route;

Route::get('/', function () {
    return view('welcome');
});

Route::get(
    '/inicio',
    [AerolineaController::class, 'aerolinea']
)->name('inicio');

Route::get(
    '/buscar',
    [AerolineaController::class, 'busqueda']
)->name('buscar');

Route::get('/reservar/{idVuelo}', [AerolineaController::class, 'reservar'])->name('reservar');


Route::get(
    '/pagar',
    [AerolineaController::class, 'pagar']
)->name('pagar');

Route::get('/iniciar-sesion', [AuthController::class, 'mostrarFormulario'])->name('iniciar-sesion');


Route::get('/registro', [RegistroController::class, 'mostrarFormularioRegistro'])->name('registro');
Route::post('/registro', [RegistroController::class, 'registrar']);

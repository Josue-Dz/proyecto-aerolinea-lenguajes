<?php

use App\Http\Controllers\AerolineaController;
use Illuminate\Support\Facades\Route;

Route::get('/', function () {
    return view('welcome');
});

Route::get('/inicio', 
    [AerolineaController::class, 'aerolinea'])->name('aerolinea.home');

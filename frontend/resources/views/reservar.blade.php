<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Buscar Vuelos</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="{{ asset('css/inicio.css') }}">
</head>

<body class="container mt-5">
    <ul class="nav nav-tabs">
        <li class="nav-item">
            <a class="nav-link {{ request()->is('/') ? 'active' : '' }}" href="{{ route('inicio') }}">Inicio</a>
        </li>
        <li class="nav-item">
            <a class="nav-link {{ request()->is('reservaciones') ? 'active' : '' }}" href="{{ route('reservar') }}">Mis vuelos</a>
        </li>
        <li class="nav-item">
            <a class="nav-link {{ request()->is('vuelos') ? 'active' : '' }}" href="{{ route('buscar') }}">Informacion de Vuelos</a>
        </li>
    </ul>
    <div class="container">
        <div class="d-flex justify-content-center align-items-center">
            <div class="container m-2">
                <div class="row">

                    <div class="col-md-6">
                        <h1 class="mb-4">Información sobre el Vuelo</h1>
                        <div>
                            <p><strong>Desde:</strong></p>
                            <p>Aeropuerto de Salida:</p>
                            <p>Fecha de Salida:</p>
                            <p>Hora de Salida:</p>
                        </div>
                        <div class="mt-3">
                            <p><strong>Hacia:</strong></p>
                            <p>Aeropuerto de Llegada:</p>
                            <p>Fecha de Llegada:</p>
                            <p>Hora de Llegada:</p>
                        </div>

                        <div class="mt-3">
                            <p><strong>Asiento Seleccionado:</strong></p>
                            <p>A1</p>
                        </div>

                        <div class="mt-3">
                            <p><strong>Asientos:</strong></p>
                            <p>Seleccionado</p>
                            <p>Clásico</p>
                            <p>Ejecutiva</p>
                            <p>Primera Clase</p>
                        </div>

                        <div class="mt-3">
                            <p><strong>Precios:</strong></p>
                            <p>Clásico...................................................$25.00</p>
                            <p>Ejecutiva...............................................$30.00</p>
                            <p>Primera Clase.....................................$50.00</p>
                        </div>

                        <div class="mt-3">
                            <a href="{{ route('buscar') }}" class="btn btn-secondary">Cancelar</a>
                            <a href="{{ route('pagar') }}" class="btn btn-success">Pagar</a>
                        </div>
                    </div>

                    <div class="col-md-6 text-center">
                        <h3 class="mb-4">Asientos Disponibles</h3>
                        <div class="container" id="avion">
                            <div id="avion-arriba"></div>
                            <div id="avion-fuera">
                                <div class="avion-dentro">
                                    @foreach($asientos as $asiento)
                                    <button type="button"
                                        class="asiento {{ strtolower($asiento->clase) }} 
                                        {{ $asiento->disponible ? '' : 'ocupado' }}"
                                        data-asiento="{{ $asiento->codigo_asiento }}">
                                        {{ $asiento->codigo_asiento }}
                                    </button>
                                    @if($asiento->codigo_asiento % 3 == 0 && !($asiento->codigo_asiento % 6 == 0))
                                    <div class="pasillo"></div>
                                    @endif
                                    @endforeach
                                    <div class="salida">SALIDA</div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>

</html>
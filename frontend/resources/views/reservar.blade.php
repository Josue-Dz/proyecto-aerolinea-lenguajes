<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Buscar Vuelos</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="{{ asset('css/inicio.css') }}">
    <link rel="stylesheet" href="{{ asset('css/avion.css') }}">
</head>

<body class="container mt-5">
<header>
        <nav class="navbar border-0">
            <ul class="nav nav-tabs border-0">
                <li>
                    <a href="{{ route('inicio') }}"><img src="images/logo.png" alt="" class="logo"></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link {{ request()->is('/') ? 'active' : '' }} rounded-pill" href="{{ route('inicio') }}">Inicio</a>
                </li>
                <li class="nav-item transparent-items">
                    <a class="nav-link {{ request()->is('reservaciones') ? 'active' : '' }} rounded-pill" href="{{ route('historial') }}">Mis vuelos</a>
                </li>
                <li class="nav-item transparent-items">
                    <a class="nav-link {{ request()->is('vuelos') ? 'active' : '' }} rounded-pill" href="{{ route('buscar') }}">Informacion de Vuelos</a>
                </li>
                <li class="nav-item transparent-items">
                    <a class="nav-link {{ request()->is('vuelos') ? 'active' : '' }} rounded-pill" href="{{ route('iniciar-sesion') }}">Iniciar Sesión</a>
                </li>
            </ul>
        </nav>
    </header>
    <div class="container">
        <div class="d-flex justify-content-center align-items-center">
            <div class="container m-2">
                <div class="row">

                    <div class="col-md-6">
                        <h1 class="mb-4">Información sobre el Vuelo</h1>
                        <div>
                            <p><strong>Desde: </strong></p>
                            <p>{{ $vuelo->aeropuertoSalida->lugar->nombre }}</p>
                            <p>Aeropuerto de Salida: {{ $vuelo->aeropuertoSalida->nombre }}</p>
                            <p>Fecha y Hora de Salida: {{ $vuelo->fechaHoraSalida }}</p>
                        </div>
                        <div class="mt-3">
                            <p><strong>Hacia:</strong></p>
                            <p>{{ $vuelo->aeropuertoLlegada->lugar->nombre }}</p>
                            <p>Aeropuerto de Llegada: {{ $vuelo->aeropuertoLlegada->nombre }}</p>
                            <p>Fecha y Hora de Llegada: {{ $vuelo->fechaHoraLlegada }}</p>
                        </div>

                        <div class="mt-3">
                            <p><strong>Asientos:</strong></p>
                            <p>Seleccionado</p>
                            <p>Clásica</p>
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
                                    <button type="button" class="asiento {{ $asiento->clase->codigoClase }} {{ $asiento->estadoAsiento->codigoEstado }}">
                                        {{ $asiento->codigoAsiento }}
                                    </button>
                                    @if($asiento->codigoAsiento % 3 == 0 && !($asiento->codigoAsiento % 6 == 0))
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
    <script>
        document.addEventListener('DOMContentLoaded', function() {
            const asientos = document.querySelectorAll('.asiento');

            asientos.forEach(function(asiento) {
                asiento.addEventListener('click', function() {
                    // Si el asiento ya está seleccionado, lo deseleccionamos
                    if (asiento.classList.contains('seleccionado')) {
                        asiento.classList.remove('seleccionado');
                    } else {
                        // Si el asiento no está seleccionado, lo seleccionamos
                        asiento.classList.add('seleccionado');
                    }
                });
            });
        });
    </script>

</body>

</html>
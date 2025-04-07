<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Buscar Vuelos</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="{{ asset('css/inicio.css') }}?v={{ time() }}">
</head>

<body>
    <header>
        <nav class="navbar border-0">
            <ul class="nav nav-tabs border-0">
                <li>
                    <a href="{{ route('inicio') }}"><img src="images/logo.png" class="logo"></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link {{ request()->is('/') ? 'active' : '' }} rounded-pill" href="{{ route('inicio') }}">Inicio</a>
                </li>
                <li class="nav-item transparent-items">
                    <a class="nav-link {{ request()->is('reservaciones') ? 'active' : '' }} rounded-pill" href="{{ route('historial', 1) }}">Mis vuelos</a>
                </li>
                <li class="nav-item transparent-items">
                    <a class="nav-link {{ request()->is('vuelos') ? 'active' : '' }} rounded-pill" href="{{ route('buscar') }}">Informacion de Vuelos</a>
                </li>
            </ul>
        </nav>
    </header>
    <div class="container mt-2">

        <div class="container-fluid">
            <div class="container mt-5">
                <div class="d-flex justify-content-between mb-3 text-white">
                    <h2>Historial de Vuelos</h2>
                </div>
                <div class="tabla p-2">
                    <table class="table table-sm table-striped table-bordered"> <!-- Historial de Vuelos -->
                        <thead class="thead-dark">
                            <tr>
                                <th>Aeropuerto de Salida</th>
                                <th>Aeropuerto de Llegada</th>
                                <th>Estado de Vuelo</th>
                                <th>Hora de Salida</th>
                                <th>Hora de Llegada</th>
                                <th>Más Detalles</th>
                            </tr>
                        </thead>
                        <tbody>
                            @foreach ($vuelos as $vuelo)
                            <tr>
                                <td>{{ $vuelo->aeropuertoSalida->nombre }}</td>
                                <td>{{ $vuelo->aeropuertoLlegada->nombre }}</td>
                                <td>{{ $vuelo->estado->nombre }}</td>
                                <td>{{ $vuelo->fechaHoraSalida }}</td>
                                <td>{{ $vuelo->fechaHoraLlegada }}</td>
                                <td class="action-buttons">
                                    <a href="{{ route('reservar') }}" class="btn btn-info btn-sm">
                                        Ver Más
                                    </a>
                                </td>
                            </tr>
                            @endforeach
                        </tbody>
                    </table>
                </div>

            </div>

        </div>

    </div>

    <footer class="derechos">
        <div class="">
            <img src="images/left1.png" class="primary-layer">
            <img src="images/left2.png" class="secondary-layer">
        </div>
        <div class="text-center">
            <p>© 2025 AeroPass. Todos los derechos reservados.</p>
            <p>Desarrollado por Aída, José, Luis, Ronny</p>

        </div>
        <div class="">
            <img src="images/right1.png" class="primary-layer">
            <img src="images/right2.png" class="secondary-layer">
        </div>
    </footer>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        $(document).ready(function() {
            $('#departureDate').datepicker({
                format: 'yyyy-mm-dd',
                startDate: 'today',
                endDate: $('#returnDate').val()
            });

            $('#returnDate').datepicker({
                format: 'yyyy-mm-dd',
                startDate: $('#departureDate').val(),
            });

            $('#departureDate').on('changeDate', function(e) {
                var departureDate = e.date;
                $('#returnDate').datepicker('setStartDate', departureDate);
            });

            $('#returnDate').on('changeDate', function(e) {
                var returnDate = e.date;
                $('#departureDate').datepicker('setEndDate', returnDate);
            });
        });
    </script>

</body>

</html>
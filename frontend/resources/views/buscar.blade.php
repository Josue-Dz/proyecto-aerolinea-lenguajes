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
        <div class="d-flex justify-content-evenly m-5"> <!-- Div para almacenar datos anteriores de búsqueda -->
            <form class="row row-cols-lg-auto g-3 align-items-center" action="{{ route('buscar') }}">
                <div class="col-12">
                    <label for="inlineFormSelectPref">Desde</label>
                    <select class="form-select" id="inlineFormSelectPref">
                        <option selected>Salida</option>
                        <option value="1">One</option>
                        <option value="2">Two</option>
                        <option value="3">Three</option>
                    </select>
                </div>

                <div class="col-12">
                    <label for="inlineFormSelectPref">A</label>
                    <select class="form-select" id="inlineFormSelectPref">
                        <option selected>Destino</option>
                        <option value="1">One</option>
                        <option value="2">Two</option>
                        <option value="3">Three</option>
                    </select>
                </div>

                <div class="col-12">
                    <label for="departureDate">Fecha de ida</label>
                    <input type="text" class="form-control" id="departureDate" placeholder="Selecciona la fecha de ida">
                </div>

                <div class="col-12">
                    <label for="returnDate">Fecha de regreso</label>
                    <input type="text" class="form-control" id="returnDate" placeholder="Selecciona la fecha de regreso">
                </div>

                <div class="col-12">
                    <label for="inlineFormSelectPref">Viajeros</label>
                    <select class="form-select" id="inlineFormSelectPref">
                        <option selected>Choose...</option>
                        <option value="1">One</option>
                        <option value="2">Two</option>
                        <option value="3">Three</option>
                    </select>
                </div>

                <div class="col-12">
                    <button type="submit" class="btn btn-primary">Buscar</button>
                </div>
            </form>
        </div>

        <div class="container mt-5">
            <div class="d-flex justify-content-between mb-3">
                <h1>Vuelos Disponibles</h1>
            </div>

            <table class="table table-striped table-bordered"> <!-- Vuelos disponibles -->
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
                    {{-- @foreach ($vuelos as $vuelo) --}}
                    <tr>
                        <td><!-- {{--$vuelo->aeropuerto salida--}} --></td>
                        <td><!-- {{--$vuelo->aeropuerto llegada--}} --></td>
                        <td><!-- {{--$vuelo->estado--}} --></td>
                        <td><!-- {{--$vuelo->hora salida--}} --></td>
                        <td><!-- {{--$vuelo->hora llegada--}} --></td>
                        <td class="action-buttons">
                            <a href="{{ route('reservar', ['idVuelo' => 1]) }}" class="btn btn-info btn-sm">
                                Reservar
                            </a>
                        </td>
                    </tr>
                    {{-- @endforeach --}}
                </tbody>
            </table>
        </div>

    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>
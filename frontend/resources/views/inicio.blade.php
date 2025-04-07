<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inicio</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="{{ asset('css/inicio.css') }}?v={{ time() }}">
</head>

<body>
    <header>
        <nav class="navbar border-0">
            <ul class="nav nav-tabs border-0">
                <li>
                    <a href="{{ route('inicio') }}"><img src=" images/logo.png" alt="" class="logo"></a>
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
                <li class="nav-item transparent-items">
                    <a class="nav-link {{ request()->is('vuelos') ? 'active' : '' }} rounded-pill" href="{{ route('iniciar-sesion') }}">Iniciar Sesión</a>
                </li>
            </ul>
        </nav>
    </header>


    <div class="container d-flex inicio">
        <div class="d-flex flex-column align-items-center m-2 transparencia">
            <div class="d-inline-flex justify-content-center align-items-start m-4 radio" id="div-seleccion">
                <div class="form-check me-2 text-truncate">
                    <input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault1">
                    <label class="form-check-label" for="flexRadioDefault1">
                        Ida y Vuelta
                    </label>
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault2">
                    <label class="form-check-label" for="flexRadioDefault2">
                        Solo ida
                    </label>
                </div>
            </div>


            <div class="container-fluid buscador bg-light">
                <form class="row m-2" action="{{ route('buscar') }}" method="post">
                    @csrf
                    @method('POST')
                    <div class="col-md">
                        <label for="origen">Desde</label>
                        <select class="form-select" id="origen" name="origen">
                            <option selected disabled>Salida</option>
                            @foreach ($aeropuertos as $aeropuerto)
                            <option value="{{ $aeropuerto->codigoAeropuerto }}">
                                {{ $aeropuerto->nombre }} ({{ $aeropuerto->lugar->nombre ?? '' }})
                            </option>
                            @endforeach
                        </select>
                    </div>

                    <div class="col-md">
                        <label for="destino">A</label>
                        <select class="form-select" id="destino" name="destino">
                            <option selected disabled>Destino</option>
                            @foreach ($aeropuertos as $aeropuerto)
                            <option value="{{ $aeropuerto->codigoAeropuerto }}">
                                {{ $aeropuerto->nombre }} ({{ $aeropuerto->lugar->nombre ?? '' }})
                            </option>
                            @endforeach
                        </select>
                    </div>

                    <div class="col-md text-truncate">
                        <label for="departureDate">Fecha de ida</label>
                        <input type="text" class="form-control" id="departureDate" name="fechaSalida" placeholder="Selecciona la fecha de ida">
                    </div>

                    <div class="col-md text-truncate">
                        <label for="returnDate">Fecha de regreso</label>
                        <input type="text" class="form-control" id="returnDate" name="fechaRegreso" placeholder="Selecciona la fecha de regreso">
                    </div>

                    <div class="col-md">
                        <label for="inlineFormSelectPref">Viajeros</label>
                        <select class="form-select" id="inlineFormSelectPref" name="viajeros">
                            <option selected>Choose...</option>
                            <option value="1">One</option>
                            <option value="2">Two</option>
                            <option value="3">Three</option>
                        </select>
                    </div>

                    <div class="col-md d-grid centrar">
                        <button type="submit" class="btn btn-primary btn-lg">Buscar</button>
                    </div>
                </form>
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

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap-datepicker@1.9.0/dist/js/bootstrap-datepicker.min.js"></script>

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

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://kit.fontawesome.com/a076d05399.js" crossorigin="anonymous"></script>

</body>

</html>
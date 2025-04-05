<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inicio</title>
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
    <div class="container inicio">
        <div class="d-flex flex-column justify-content-center align-items-center m-3" id="div-forms">
            <div class="d-flex justify-content-start align-items-start mb-2" id="div-seleccion">
                <div class="form-check me-2">
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

            <div class="justify-center buscador">
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
        </div>

    </div>

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
</body>

</html>
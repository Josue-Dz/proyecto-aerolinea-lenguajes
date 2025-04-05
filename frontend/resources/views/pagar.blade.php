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
            <div class="container m-3">

                <div class="row">

                    <div class="tarjeta">
                        <form>

                            <h3 class="card_title">
                                Ingrese los datos de la tarjeta
                            </h3>

                            <div class="card_row">
                                <div class="card_col mb-3">
                                    <label class="card_label" for="numero"> Número de Tarjeta </label>
                                    <input class="card_input card_input-large" id="numero" name="numero" type="text" placeholder="XXXX-XXXX-XXXX-XXXX">
                                </div>

                                <div class="card_col mb-3">
                                    <label class="card_label" for="nombre"> Nombre Propietario </label>
                                    <input class="card_input card_input-large" id="nombre" name="nombre" type="text" placeholder="Nombre Completo">
                                </div>

                            </div>

                            <div class="card_row">
                                <div class="card_col mb-3">
                                    <label class="card_label" for="fecha_vencimiento"> Fecha de Vencimiento </label>
                                    <input class="card_input" id="fecha_vencimiento" name="fecha_vencimiento" type="text" placeholder="MM/YY">
                                </div>

                                <div class="card_col mb-3">
                                    <label class="card_label" for="CVV"> CVC/CVV </label>
                                    <input class="card_input" id="CVV" name="CVV" type="text" placeholder="***">
                                </div>

                                <div class="card_col card_type"><i id="cardType"></i></div>
                            </div>

                            <div class="mb-3">
                                <a href="{{ route('reservar') }}" class="btn btn-secondary">Volver</a>
                                <button type="submit" class="btn btn-success">Confirmar</button>
                            </div>

                        </form>
                    </div>

                </div>

            </div>

        </div>

    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>
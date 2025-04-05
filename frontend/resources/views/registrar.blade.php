<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registro</title>

    <!-- Bootstrap 5 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- FontAwesome (para los íconos) -->
    <script src="https://kit.fontawesome.com/a076d05399.js" crossorigin="anonymous"></script>

    <style body { background: #f8f9fa; } .card {
        border-radius: 15px;
        }
        .btn-primary {
        border-radius: 25px;
        height: 50px;
        font-size: 16px;
        transition: 0.3s;
        }
        .btn-primary:hover {
        box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.2);
        }>
    </style>
</head>

<body>

    <div class="container d-flex justify-content-center align-items-center" style="height: 100vh;">
        <div class="card p-4 shadow-lg" style="width: 420px;">
            <h3 class="text-center mb-3 text-primary fw-bold">Crear Cuenta</h3>
            <p class="text-center text-muted">¡Únete y disfruta de nuestros servicios!</p>

            <form action="" method="POST">
                @csrf

                <div class="mb-3 input-group">
                    <span class="input-group-text bg-primary text-white"><i class="fas fa-user"></i></span>
                    <input type="text" name="name" id="name" class="form-control" placeholder="Nombre completo" required>
                </div>

                <div class="mb-3 input-group">
                    <span class="input-group-text bg-primary text-white"><i class="fas fa-envelope"></i></span>
                    <input type="email" name="email" id="email" class="form-control" placeholder="Correo electrónico" required>
                </div>

                <div class="mb-3 input-group">
                    <span class="input-group-text bg-primary text-white"><i class="fas fa-lock"></i></span>
                    <input type="password" name="password" id="password" class="form-control" placeholder="Contraseña" required>
                </div>

                <div class="mb-3 input-group">
                    <span class="input-group-text bg-primary text-white"><i class="fas fa-lock"></i></span>
                    <input type="password" name="password_confirmation" id="password_confirmation" class="form-control" placeholder="Confirmar contraseña" required>
                </div>

                <button type="submit" class="btn btn-primary w-100 fw-bold shadow">
                    Registrarse <i class="fas fa-arrow-right"></i>
                </button>
            </form>

            <div class="text-center mt-3">
                <small class="text-muted">¿Ya tienes cuenta? <a href="{{ route('iniciar-sesion') }}" class="text-primary fw-bold">Inicia sesión</a></small>
            </div>
        </div>
    </div>

    <!-- Bootstrap Bundle con JavaScript -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>

</html>
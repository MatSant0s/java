CREATE TABLE public.personas (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    genero VARCHAR(50) NOT NULL,
    edad INTEGER NOT NULL,
    identificacion VARCHAR(50) UNIQUE NOT NULL,
    direccion VARCHAR(255) NOT NULL,
    telefono VARCHAR(50) NOT NULL
);

CREATE TABLE public.clientes (
    id BIGINT PRIMARY KEY,
    password VARCHAR(255) NOT NULL,
    estado BOOLEAN NOT NULL,
    FOREIGN KEY (id) REFERENCES public.personas(id)
);

CREATE TABLE public.cuentas (
    cuenta_id BIGSERIAL PRIMARY KEY,
    numero_cuenta VARCHAR(100) UNIQUE NOT NULL,
    tipo_cuenta VARCHAR(100) NOT NULL,
    saldo DECIMAL(15, 2) NOT NULL,
    estado BOOLEAN NOT NULL,
    cliente_id BIGINT NOT NULL,
    FOREIGN KEY (cliente_id) REFERENCES public.clientes(id)
);

CREATE TABLE public.movimientos (
    id BIGSERIAL PRIMARY KEY,
    fecha DATE NOT NULL,
    tipo_movimiento VARCHAR(50) NOT NULL,
    valor DECIMAL(15, 2) NOT NULL,
    saldo DECIMAL(15, 2) NOT NULL,
    cuenta_id BIGINT NOT NULL,
    FOREIGN KEY (cuenta_id) REFERENCES public.cuentas(cuenta_id)
);

INSERT INTO public.personas (nombre, genero, edad, identificacion, direccion, telefono)
VALUES
('Juan Pérez', 'Masculino', 30, '123456789', 'Calle Ficticia 123', '987654321'),
('María Gómez', 'Femenino', 28, '987654321', 'Avenida Real 456', '123456789');


INSERT INTO public.clientes (id, password, estado)
VALUES
(1, 'password123', true),
(2, 'password456', true);


INSERT INTO public.cuentas (numero_cuenta, tipo_cuenta, saldo, estado, cliente_id)
VALUES
('10000001', 'Ahorros', 1500.00, true, 1),
('10000002', 'Corriente', 2500.00, true, 2);


INSERT INTO public.movimientos (fecha, tipo_movimiento, valor, saldo, cuenta_id)
VALUES
('2025-05-19', 'Depósito', 500.00, 1500.00, 1),
('2025-05-20', 'Retiro', 200.00, 1800.00, 2);


INSERT INTO public.movimientos (fecha, tipo_movimiento, valor, saldo, cuenta_id)
VALUES
('2025-05-19', 'Depósito', 500.00, 2000.00, 1),
('2025-05-19', 'Retiro', 300.00, 2200.00, 2);	

select * from movimientos
select * from cuentas
select * from clientes




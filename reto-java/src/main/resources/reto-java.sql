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




INSERT INTO public.personas (nombre, genero, edad, identificacion, direccion, telefono)
VALUES
('Jose Lema', 'Masculino', 30, '1234', 'Otavalo sn y principal', '098254785');

INSERT INTO public.personas (nombre, genero, edad, identificacion, direccion, telefono)
VALUES
('Marianela Montalvo', 'Femenino', 28, '5678', 'Amazonas y NNUU', '097548965');

INSERT INTO public.personas (nombre, genero, edad, identificacion, direccion, telefono)
VALUES
('Juan Osorio', 'Masculino', 32, '1245', '13 junio y Equinoccial', '098874587');

INSERT INTO public.clientes (id, password, estado)
SELECT id, '1234', True FROM public.personas WHERE nombre = 'Jose Lema';

INSERT INTO public.clientes (id, password, estado)
SELECT id, '5678', True FROM public.personas WHERE nombre = 'Marianela Montalvo';

INSERT INTO public.clientes (id, password, estado)
SELECT id, '1245', True FROM public.personas WHERE nombre = 'Juan Osorio';

INSERT INTO public.cuentas (numero_cuenta, tipo_cuenta, saldo, estado, cliente_id)
VALUES
('10000001', 'Ahorros', 1500.00, true, 1);

INSERT INTO public.movimientos (fecha, tipo_movimiento, valor, saldo, cuenta_id)
VALUES
('2025-05-19', 'Depósito', 500.00, 1500.00, 1);

   		
select * from personas
select * from clientes
select * from cuentas
select * from movimientos


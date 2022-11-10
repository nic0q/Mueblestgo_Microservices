# Node Alpine version -> light version
FROM node:16-alpine
WORKDIR /app

# Copiar paquete de dependencias
COPY package*.json ./
# Instalar dependencias
RUN npm install

# Copiar archivos del proyecto a directorio /app en imagen
COPY . .

EXPOSE 3000

CMD ["npm", "start"]
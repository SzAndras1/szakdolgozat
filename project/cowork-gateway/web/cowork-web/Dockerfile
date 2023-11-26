FROM node:18-alpine

WORKDIR /var/lib/project

RUN npm install -g @angular/cli@15.0.3

COPY package.json package-lock.json ./
RUN npm ci --legacy-peer-deps

COPY . .

CMD ["ng", "serve", "--host", "0.0.0.0"]

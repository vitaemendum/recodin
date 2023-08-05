FROM node:18.17.0-alpine3.18

COPY package.json package-lock.json vite.config.js ./

RUN npm install

COPY . .

RUN npm run build

EXPOSE 5173

CMD ["npm", "run", "preview"]
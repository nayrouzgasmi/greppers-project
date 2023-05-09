FROM node:18.16.0 AS build
WORKDIR /home/app
COPY . /home/app/
RUN npm install -g @angular/cli
RUN npm install --legacy-peer-deps
RUN npm run build --prod

# Routing in NGINX doesn't work.
# FROM nginx:1.21.0-alpine
# COPY --from=build /home/app/dist/green-bubble /usr/share/nginx/html

CMD ng serve --host 0.0.0.0
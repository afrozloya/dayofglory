# get the base node image
FROM node:alpine as builder

# set the working dir for container
WORKDIR /dayofglory-frontend

# copy the json file first
COPY ./package.json /dayofglory-frontend

# install npm dependencies
RUN npm install

# copy other project files
COPY . .

RUN chmod -r 777  /dayofglory-frontend

USER node

# build the folder
CMD [ "npm", "run", "start" ]
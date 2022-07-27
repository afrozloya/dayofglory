# get the base node image
FROM node:alpine as builder

# set the working dir for container
WORKDIR /contactlist-frontend

# copy the json file first
COPY ./package.json /contactlist-frontend

# install npm dependencies
RUN npm install

# copy other project files
COPY . .

RUN chmod -r 777  /contactlist-frontend

USER node

# build the folder
CMD [ "npm", "run", "start" ]
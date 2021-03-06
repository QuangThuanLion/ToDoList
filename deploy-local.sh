#runing to generate jar file
mvn clean package -DskipTests

#runing to create images from docker-compose.yml
version: '2'

services:
  mysql-todo-v1:
    container_name: mysql-todo-v2
    image: mysql:8
    ports:
      - 3307:3306
    networks:
      - todolist-mysql
    volumes:
      - mysql-data:/var/lib/mysql
      - ./database.sql:/docker-entrypoint-initdb.d/init.sql
    environment:
      MYSQL_ROOT_PASSWORD: root
      MYSQL_DATABASE: todolist

  app-todo-v1:
    container_name: app-todo-v2
    build:
      context: .
      dockerfile: Dockerfile
    ports:
      - 8080:8080
    networks:
      - todolist-mysql
    restart: always
    environment:
      SPRING_DATASOURCE_URL: jdbc:mysql://mysql-todo-v1:3306/todolist
      SPRING_DATASOURCE_USERNAME: root
      SPRING_DATASOURCE_PASSWORD: root
    depends_on:
      - mysql-todo-v1

networks:
  todolist-mysql:

volumes:
  mysql-data:
    driver: local
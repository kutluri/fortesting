{
	"info": {
		"_postman_id": "558c6fa5-be9f-4ef2-8cb2-5c427c27b34a",
		"name": "juice-shop",
		"description": "URL: awesome OWASP Juice shop  \n[https://juice-shop.herokuapp.com/](https://juice-shop.herokuapp.com/)  \nFind the APIs endpoints and test the following scenarios:\n\nQ1: Sign in the user, add 1 item to the basket, and verify that 1 item is in the basket  \nQ2: Same as the previous scenario but add 2 items instead of 1 to the basket  \nQ3: Same as the previous scenario but delete 1 item and validate that only 1 item remains in the basket\n\nAuthor: Tony Nguyen  \nIssue date: Sat 21 May 2022  \nComplete date: Sun 22 May 2022\n\nHOW TO RUN COLLECTION:\n\n*   Run all collections:\n    *   1\\. From the juice-shop collection > Click on the \"View More Action\" icon (...)\n    *   2\\. Select \"Run collection\"\n    *   3\\. At the Runner window, click on the \"Run juice-shop\" blue button\n*   Run each small collection:\n    *   1\\. Click on the small collection folder such as \"Add 1 item to the basket and verify it in the basket\"\n    *   2\\. Click on the \"Run\" button\n    *   3\\. At the Runner window, click on the \"Run juice-shop\" blue button",
		"schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json",
		"_exporter_id": "32677178",
		"_collection_link": "https://www.postman.com/tonynguyen91/workspace/owasp-juice-shop/collection/5117173-558c6fa5-be9f-4ef2-8cb2-5c427c27b34a?action=share&source=collection_link&creator=32677178"
	},
	"item": [
		{
			"name": "Add 1 item to the basket and verify it in the basket",
			"item": [
				{
					"name": "createNewUser",
					"event": [
						{
							"listen": "prerequest",
							"script": {
								"exec": [
									"/* Prepare data for the create new user API",
									"    Author: Tony Nguyen",
									"    Issue date: Sat 21 May, 2022",
									"*/",
									"",
									"//Clear all your environment values",
									"pm.environment.clear()",
									"",
									"//Generate the random email and password and save it into the environment variable",
									"pm.environment.set(\"_email\", pm.variables.replaceIn('{{$randomEmail}}'));",
									"pm.environment.set(\"_password\", pm.variables.replaceIn('{{$randomPassword}}'));",
									"",
									"",
									""
								],
								"type": "text/javascript"
							}
						},
						{
							"listen": "test",
							"script": {
								"exec": [
									"/* Test the create new user API",
									"    Author: Tony Nguyen",
									"    Issue date: Sat 21 May, 2022",
									"*/",
									"",
									"//Successful to create new user with status code is 201",
									"pm.test(\"Successful to create new user with status code is 201\", function () {",
									"    pm.response.to.have.status(201);",
									"});",
									"",
									"var jsonData = JSON.parse(responseBody);",
									"//Verify that the new user is create with the correct email",
									"pm.test(\"TVerify that the new user is create with the correct email\", function () {",
									"    pm.expect(jsonData.data.email).is.equal(pm.environment.get(\"_email\"));",
									"});"
								],
								"type": "text/javascript"
							}
						}
					],
					"request": {
						"method": "POST",
						"header": [
							{
								"key": "Content-Type",
								"value": "application/json"
							}
						],
						"body": {
							"mode": "raw",
							"raw": "{\"email\":\"{{_email}}\",\"password\":\"{{_password}}\",\"passwordRepeat\":\"{{_password}}\",\"securityQuestion\":{\"id\":7,\"question\":\"Name of your favorite pet?\",\"createdAt\":\"2022-05-20T17:09:53.991Z\",\"updatedAt\":\"2022-05-20T17:09:53.991Z\"},\"securityAnswer\":\"MIX MIX\"}"
						},
						"url": {
							"raw": "https://juice-shop.herokuapp.com/api/Users/",
							"protocol": "https",
							"host": [
								"juice-shop",
								"herokuapp",
								"com"
							],
							"path": [
								"api",
								"Users",
								""
							]
						}
					},
					"response": []
				},
				{
					"name": "login",
					"event": [
						{
							"listen": "prerequest",
							"script": {
								"exec": [
									""
								],
								"type": "text/javascript"
							}
						},
						{
							"listen": "test",
							"script": {
								"exec": [
									"/* Test the login API",
									"    Author: Tony Nguyen",
									"    Issue date: Sat 21 May, 2022",
									"*/",
									"",
									"//Successful to login with status code is 200",
									"pm.test(\"Successful to login with status code is 200\", function () {",
									"    pm.response.to.have.status(200);",
									"});",
									"",
									"",
									"var jsonData = JSON.parse(responseBody);",
									"//Get and save the token to the environment variable",
									"postman.setEnvironmentVariable(\"_token\", \"Bearer \" + jsonData.authentication.token);",
									"//Get and save the basketID to the environment variable",
									"postman.setEnvironmentVariable(\"_bid\", jsonData.authentication.bid);",
									""
								],
								"type": "text/javascript"
							}
						}
					],
					"request": {
						"method": "POST",
						"header": [
							{
								"key": "Content-Type",
								"value": "application/json"
							}
						],
						"body": {
							"mode": "raw",
							"raw": "{\"email\":\"{{_email}}\",\"password\":\"{{_password}}\"}"
						},
						"url": {
							"raw": "https://juice-shop.herokuapp.com/rest/user/login",
							"protocol": "https",
							"host": [
								"juice-shop",
								"herokuapp",
								"com"
							],
							"path": [
								"rest",
								"user",
								"login"
							]
						}
					},
					"response": []
				},
				{
					"name": "getAllProduct",
					"event": [
						{
							"listen": "prerequest",
							"script": {
								"exec": [
									""
								],
								"type": "text/javascript"
							}
						},
						{
							"listen": "test",
							"script": {
								"exec": [
									"/* Test for the search API",
									"    Author: Tony Nguyen",
									"    Issue date: Sat 21 May, 2022",
									"*/",
									"",
									"//Successful to get all product with status code is 200",
									"pm.test(\"Successful to get all product with status code is 200\", function () {",
									"    pm.response.to.have.status(200);",
									"});",
									"",
									"//Get and save the id and name of the first product item ",
									"var jsonData = JSON.parse(responseBody);",
									"postman.setEnvironmentVariable(\"_product_1_id\", jsonData.data[0].id);",
									"postman.setEnvironmentVariable(\"_product_1_name\", jsonData.data[0].name);",
									""
								],
								"type": "text/javascript"
							}
						}
					],
					"request": {
						"method": "GET",
						"header": [
							{
								"key": "Authorization",
								"value": "{{_token}}"
							}
						],
						"url": {
							"raw": "https://juice-shop.herokuapp.com/rest/products/search?q=",
							"protocol": "https",
							"host": [
								"juice-shop",
								"herokuapp",
								"com"
							],
							"path": [
								"rest",
								"products",
								"search"
							],
							"query": [
								{
									"key": "q",
									"value": ""
								}
							]
						}
					},
					"response": []
				},
				{
					"name": "Add1ItemToBasket",
					"event": [
						{
							"listen": "prerequest",
							"script": {
								"exec": [
									""
								],
								"type": "text/javascript"
							}
						},
						{
							"listen": "test",
							"script": {
								"exec": [
									"/* test the add item to basket API",
									"    Author: Tony Nguyen",
									"    Issue date: Sat 21 May, 2022",
									"*/",
									"",
									"//Successful to add item to basket with status code is 200",
									"pm.test(\"Successful to sadd item to basket with status code is 200\", function () {",
									"    pm.response.to.have.status(200);",
									"});"
								],
								"type": "text/javascript"
							}
						}
					],
					"request": {
						"method": "POST",
						"header": [
							{
								"key": "Authorization",
								"value": "{{_token}}"
							},
							{
								"key": "Content-Type",
								"value": "application/json; charset=utf-8",
								"type": "text"
							}
						],
						"body": {
							"mode": "raw",
							"raw": "{\"ProductId\":{{_product_1_id}},\"BasketId\":\"{{_bid}}\",\"quantity\":1}"
						},
						"url": {
							"raw": "https://juice-shop.herokuapp.com/api/BasketItems/",
							"protocol": "https",
							"host": [
								"juice-shop",
								"herokuapp",
								"com"
							],
							"path": [
								"api",
								"BasketItems",
								""
							]
						}
					},
					"response": []
				},
				{
					"name": "Verify1ItemInBasket",
					"event": [
						{
							"listen": "prerequest",
							"script": {
								"exec": [
									""
								],
								"type": "text/javascript"
							}
						},
						{
							"listen": "test",
							"script": {
								"exec": [
									"/* Test the get basket item API",
									"    Author: Tony Nguyen",
									"    Issue date: Sat 21 May, 2022",
									"*/",
									"",
									"//Successful to get the basket item with status code is 200",
									"pm.test(\"Successful to get the basket item with status code is 200\", function () {",
									"    pm.response.to.have.status(200);",
									"});",
									"",
									"var jsonData = JSON.parse(responseBody);",
									"//Verify that the user basket have 1 item with the correct item name",
									"pm.test(\"The user basket has 1 item with the correct item name\", function () {",
									"    pm.expect(jsonData.data.Products.length).is.equal(1);",
									"});",
									"//Verify that the user basket have 1 item with the correct item name",
									"pm.test(\"The item in user basket has the correct item id and item name\", function () {",
									"    pm.expect(jsonData.data.Products[0].id).is.equal(Number(pm.environment.get(\"_product_1_id\")));",
									"    pm.expect(jsonData.data.Products[0].name).is.equal(pm.environment.get(\"_product_1_name\"));",
									"});"
								],
								"type": "text/javascript"
							}
						}
					],
					"request": {
						"method": "GET",
						"header": [
							{
								"key": "Authorization",
								"value": "{{_token}}"
							}
						],
						"url": {
							"raw": "https://juice-shop.herokuapp.com/rest/basket/{{_bid}}",
							"protocol": "https",
							"host": [
								"juice-shop",
								"herokuapp",
								"com"
							],
							"path": [
								"rest",
								"basket",
								"{{_bid}}"
							]
						}
					},
					"response": []
				}
			]
		},
		{
			"name": "Add 2 items to the basket and verify it in the basket",
			"item": [
				{
					"name": "createNewUser",
					"event": [
						{
							"listen": "prerequest",
							"script": {
								"exec": [
									"/* Prepare data for the create new user API",
									"    Author: Tony Nguyen",
									"    Issue date: Sat 21 May, 2022",
									"*/",
									"",
									"//Clear all your environment values",
									"pm.environment.clear()",
									"",
									"//Generate the random email and password and save it into the environment variable",
									"pm.environment.set(\"_email\", pm.variables.replaceIn('{{$randomEmail}}'));",
									"pm.environment.set(\"_password\", pm.variables.replaceIn('{{$randomPassword}}'));",
									"",
									"",
									""
								],
								"type": "text/javascript"
							}
						},
						{
							"listen": "test",
							"script": {
								"exec": [
									"/* Test the create new user API",
									"    Author: Tony Nguyen",
									"    Issue date: Sat 21 May, 2022",
									"*/",
									"",
									"//Successful to create new user with status code is 201",
									"pm.test(\"Successful to create new user with status code is 201\", function () {",
									"    pm.response.to.have.status(201);",
									"});",
									"",
									"var jsonData = JSON.parse(responseBody);",
									"//Verify that the new user is create with the correct email",
									"pm.test(\"TVerify that the new user is create with the correct email\", function () {",
									"    pm.expect(jsonData.data.email).is.equal(pm.environment.get(\"_email\"));",
									"});"
								],
								"type": "text/javascript"
							}
						}
					],
					"request": {
						"method": "POST",
						"header": [
							{
								"key": "Content-Type",
								"value": "application/json"
							}
						],
						"body": {
							"mode": "raw",
							"raw": "{\"email\":\"{{_email}}\",\"password\":\"{{_password}}\",\"passwordRepeat\":\"{{_password}}\",\"securityQuestion\":{\"id\":7,\"question\":\"Name of your favorite pet?\",\"createdAt\":\"2022-05-20T17:09:53.991Z\",\"updatedAt\":\"2022-05-20T17:09:53.991Z\"},\"securityAnswer\":\"MIX MIX\"}"
						},
						"url": {
							"raw": "https://juice-shop.herokuapp.com/api/Users/",
							"protocol": "https",
							"host": [
								"juice-shop",
								"herokuapp",
								"com"
							],
							"path": [
								"api",
								"Users",
								""
							]
						}
					},
					"response": []
				},
				{
					"name": "login",
					"event": [
						{
							"listen": "prerequest",
							"script": {
								"exec": [
									""
								],
								"type": "text/javascript"
							}
						},
						{
							"listen": "test",
							"script": {
								"exec": [
									"/* Test the login API",
									"    Author: Tony Nguyen",
									"    Issue date: Sat 21 May, 2022",
									"*/",
									"",
									"//Successful to login with status code is 200",
									"pm.test(\"Successful to login with status code is 200\", function () {",
									"    pm.response.to.have.status(200);",
									"});",
									"",
									"",
									"var jsonData = JSON.parse(responseBody);",
									"//Get and save the token to the environment variable",
									"postman.setEnvironmentVariable(\"_token\", \"Bearer \" + jsonData.authentication.token);",
									"//Get and save the basketID to the environment variable",
									"postman.setEnvironmentVariable(\"_bid\", jsonData.authentication.bid);",
									""
								],
								"type": "text/javascript"
							}
						}
					],
					"request": {
						"method": "POST",
						"header": [
							{
								"key": "Content-Type",
								"value": "application/json"
							}
						],
						"body": {
							"mode": "raw",
							"raw": "{\"email\":\"{{_email}}\",\"password\":\"{{_password}}\"}"
						},
						"url": {
							"raw": "https://juice-shop.herokuapp.com/rest/user/login",
							"protocol": "https",
							"host": [
								"juice-shop",
								"herokuapp",
								"com"
							],
							"path": [
								"rest",
								"user",
								"login"
							]
						}
					},
					"response": []
				},
				{
					"name": "getAllProduct",
					"event": [
						{
							"listen": "prerequest",
							"script": {
								"exec": [
									""
								],
								"type": "text/javascript"
							}
						},
						{
							"listen": "test",
							"script": {
								"exec": [
									"/* Test for the search API",
									"    Author: Tony Nguyen",
									"    Issue date: Sat 21 May, 2022",
									"*/",
									"",
									"//Successful to get all product with status code is 200",
									"pm.test(\"Successful to get all product with status code is 200\", function () {",
									"    pm.response.to.have.status(200);",
									"});",
									"",
									"//Get and save the id and name of the first product item ",
									"var jsonData = JSON.parse(responseBody);",
									"postman.setEnvironmentVariable(\"_product_1_id\", jsonData.data[0].id);",
									"postman.setEnvironmentVariable(\"_product_1_name\", jsonData.data[0].name);",
									"",
									"//Get and save the id and name of the second product item",
									"postman.setEnvironmentVariable(\"_product_2_id\", jsonData.data[1].id);",
									"postman.setEnvironmentVariable(\"_product_2_name\", jsonData.data[1].name);",
									""
								],
								"type": "text/javascript"
							}
						}
					],
					"request": {
						"method": "GET",
						"header": [
							{
								"key": "Authorization",
								"value": "{{_token}}"
							}
						],
						"url": {
							"raw": "https://juice-shop.herokuapp.com/rest/products/search?q=",
							"protocol": "https",
							"host": [
								"juice-shop",
								"herokuapp",
								"com"
							],
							"path": [
								"rest",
								"products",
								"search"
							],
							"query": [
								{
									"key": "q",
									"value": ""
								}
							]
						}
					},
					"response": []
				},
				{
					"name": "AddFirstItemToBasket",
					"event": [
						{
							"listen": "prerequest",
							"script": {
								"exec": [
									""
								],
								"type": "text/javascript"
							}
						},
						{
							"listen": "test",
							"script": {
								"exec": [
									"/* test the add item to basket API",
									"    Author: Tony Nguyen",
									"    Issue date: Sat 21 May, 2022",
									"*/",
									"",
									"//Successful to add the first item to basket with status code is 200",
									"pm.test(\"Successful to add the first item to basket with status code is 200\", function () {",
									"    pm.response.to.have.status(200);",
									"});"
								],
								"type": "text/javascript"
							}
						}
					],
					"request": {
						"method": "POST",
						"header": [
							{
								"key": "Authorization",
								"value": "{{_token}}"
							},
							{
								"key": "Content-Type",
								"value": "application/json; charset=utf-8",
								"type": "text"
							}
						],
						"body": {
							"mode": "raw",
							"raw": "{\"ProductId\":{{_product_1_id}},\"BasketId\":\"{{_bid}}\",\"quantity\":1}"
						},
						"url": {
							"raw": "https://juice-shop.herokuapp.com/api/BasketItems/",
							"protocol": "https",
							"host": [
								"juice-shop",
								"herokuapp",
								"com"
							],
							"path": [
								"api",
								"BasketItems",
								""
							]
						}
					},
					"response": []
				},
				{
					"name": "AddSecondItemToBasket",
					"event": [
						{
							"listen": "prerequest",
							"script": {
								"exec": [
									""
								],
								"type": "text/javascript"
							}
						},
						{
							"listen": "test",
							"script": {
								"exec": [
									"/* test the add item to basket API",
									"    Author: Tony Nguyen",
									"    Issue date: Sat 21 May, 2022",
									"*/",
									"",
									"//Successful to add the second item to basket with status code is 200",
									"pm.test(\"Successful to add the second item to basket with status code is 200\", function () {",
									"    pm.response.to.have.status(200);",
									"});"
								],
								"type": "text/javascript"
							}
						}
					],
					"request": {
						"method": "POST",
						"header": [
							{
								"key": "Authorization",
								"value": "{{_token}}"
							},
							{
								"key": "Content-Type",
								"value": "application/json; charset=utf-8",
								"type": "text"
							}
						],
						"body": {
							"mode": "raw",
							"raw": "{\"ProductId\":{{_product_2_id}},\"BasketId\":\"{{_bid}}\",\"quantity\":1}"
						},
						"url": {
							"raw": "https://juice-shop.herokuapp.com/api/BasketItems/",
							"protocol": "https",
							"host": [
								"juice-shop",
								"herokuapp",
								"com"
							],
							"path": [
								"api",
								"BasketItems",
								""
							]
						}
					},
					"response": []
				},
				{
					"name": "Verify2ItemsInBasket",
					"event": [
						{
							"listen": "prerequest",
							"script": {
								"exec": [
									""
								],
								"type": "text/javascript"
							}
						},
						{
							"listen": "test",
							"script": {
								"exec": [
									"/* Test the get basket item API",
									"    Author: Tony Nguyen",
									"    Issue date: Sat 21 May, 2022",
									"*/",
									"",
									"//Successful to get the basket item with status code is 200",
									"pm.test(\"Successful to get the basket item with status code is 200\", function () {",
									"    pm.response.to.have.status(200);",
									"});",
									"",
									"var jsonData = JSON.parse(responseBody);",
									"//Verify that the user basket have 2 items with the correct item name",
									"pm.test(\"Verify that the user basket have 2 items with the correct item name\", function () {",
									"    pm.expect(jsonData.data.Products.length).is.equal(2);",
									"});",
									"",
									"//Verify that the first item in basket has the correct item id and name",
									"pm.test(\"Verify that the first item in basket has the correct item id and name\", function () {",
									"    pm.expect(jsonData.data.Products[0].id).is.equal(Number(pm.environment.get(\"_product_1_id\")));",
									"    pm.expect(jsonData.data.Products[0].name).is.equal(pm.environment.get(\"_product_1_name\"));",
									"});",
									"",
									"//Verify that the second item in basket has the correct item id and name",
									"pm.test(\"Verify that the second item in basket has the correct item id and name\", function () {",
									"    pm.expect(jsonData.data.Products[1].id).is.equal(Number(pm.environment.get(\"_product_2_id\")));",
									"    pm.expect(jsonData.data.Products[1].name).is.equal(pm.environment.get(\"_product_2_name\"));",
									"});"
								],
								"type": "text/javascript"
							}
						}
					],
					"request": {
						"method": "GET",
						"header": [
							{
								"key": "Authorization",
								"value": "{{_token}}"
							}
						],
						"url": {
							"raw": "https://juice-shop.herokuapp.com/rest/basket/{{_bid}}",
							"protocol": "https",
							"host": [
								"juice-shop",
								"herokuapp",
								"com"
							],
							"path": [
								"rest",
								"basket",
								"{{_bid}}"
							]
						}
					},
					"response": []
				}
			]
		},
		{
			"name": "Add 2 items then delete 1 item from the basket",
			"item": [
				{
					"name": "createNewUser",
					"event": [
						{
							"listen": "prerequest",
							"script": {
								"exec": [
									"/* Prepare data for the create new user API",
									"    Author: Tony Nguyen",
									"    Issue date: Sat 21 May, 2022",
									"*/",
									"",
									"//Clear all your environment values",
									"pm.environment.clear()",
									"",
									"//Generate the random email and password and save it into the environment variable",
									"pm.environment.set(\"_email\", pm.variables.replaceIn('{{$randomEmail}}'));",
									"pm.environment.set(\"_password\", pm.variables.replaceIn('{{$randomPassword}}'));",
									"",
									"",
									""
								],
								"type": "text/javascript"
							}
						},
						{
							"listen": "test",
							"script": {
								"exec": [
									"/* Test the create new user API",
									"    Author: Tony Nguyen",
									"    Issue date: Sat 21 May, 2022",
									"*/",
									"",
									"//Successful to create new user with status code is 201",
									"pm.test(\"Successful to create new user with status code is 201\", function () {",
									"    pm.response.to.have.status(201);",
									"});",
									"",
									"var jsonData = JSON.parse(responseBody);",
									"//Verify that the new user is create with the correct email",
									"pm.test(\"TVerify that the new user is create with the correct email\", function () {",
									"    pm.expect(jsonData.data.email).is.equal(pm.environment.get(\"_email\"));",
									"});"
								],
								"type": "text/javascript"
							}
						}
					],
					"request": {
						"method": "POST",
						"header": [
							{
								"key": "Content-Type",
								"value": "application/json"
							}
						],
						"body": {
							"mode": "raw",
							"raw": "{\"email\":\"{{_email}}\",\"password\":\"{{_password}}\",\"passwordRepeat\":\"{{_password}}\",\"securityQuestion\":{\"id\":7,\"question\":\"Name of your favorite pet?\",\"createdAt\":\"2022-05-20T17:09:53.991Z\",\"updatedAt\":\"2022-05-20T17:09:53.991Z\"},\"securityAnswer\":\"MIX MIX\"}"
						},
						"url": {
							"raw": "https://juice-shop.herokuapp.com/api/Users/",
							"protocol": "https",
							"host": [
								"juice-shop",
								"herokuapp",
								"com"
							],
							"path": [
								"api",
								"Users",
								""
							]
						}
					},
					"response": []
				},
				{
					"name": "login",
					"event": [
						{
							"listen": "prerequest",
							"script": {
								"exec": [
									""
								],
								"type": "text/javascript"
							}
						},
						{
							"listen": "test",
							"script": {
								"exec": [
									"/* Test the login API",
									"    Author: Tony Nguyen",
									"    Issue date: Sat 21 May, 2022",
									"*/",
									"",
									"//Successful to login with status code is 200",
									"pm.test(\"Successful to login with status code is 200\", function () {",
									"    pm.response.to.have.status(200);",
									"});",
									"",
									"",
									"var jsonData = JSON.parse(responseBody);",
									"//Get and save the token to the environment variable",
									"postman.setEnvironmentVariable(\"_token\", \"Bearer \" + jsonData.authentication.token);",
									"//Get and save the basketID to the environment variable",
									"postman.setEnvironmentVariable(\"_bid\", jsonData.authentication.bid);",
									""
								],
								"type": "text/javascript"
							}
						}
					],
					"request": {
						"method": "POST",
						"header": [
							{
								"key": "Content-Type",
								"value": "application/json"
							}
						],
						"body": {
							"mode": "raw",
							"raw": "{\"email\":\"{{_email}}\",\"password\":\"{{_password}}\"}"
						},
						"url": {
							"raw": "https://juice-shop.herokuapp.com/rest/user/login",
							"protocol": "https",
							"host": [
								"juice-shop",
								"herokuapp",
								"com"
							],
							"path": [
								"rest",
								"user",
								"login"
							]
						}
					},
					"response": []
				},
				{
					"name": "getAllProduct",
					"event": [
						{
							"listen": "prerequest",
							"script": {
								"exec": [
									""
								],
								"type": "text/javascript"
							}
						},
						{
							"listen": "test",
							"script": {
								"exec": [
									"/* Test for the search API",
									"    Author: Tony Nguyen",
									"    Issue date: Sat 21 May, 2022",
									"*/",
									"",
									"//Successful to get all product with status code is 200",
									"pm.test(\"Successful to get all product with status code is 200\", function () {",
									"    pm.response.to.have.status(200);",
									"});",
									"",
									"//Get and save the id and name of the first product item ",
									"var jsonData = JSON.parse(responseBody);",
									"postman.setEnvironmentVariable(\"_product_1_id\", jsonData.data[0].id);",
									"postman.setEnvironmentVariable(\"_product_1_name\", jsonData.data[0].name);",
									"",
									"//Get and save the id and name of the second product item",
									"postman.setEnvironmentVariable(\"_product_2_id\", jsonData.data[1].id);",
									"postman.setEnvironmentVariable(\"_product_2_name\", jsonData.data[1].name);",
									""
								],
								"type": "text/javascript"
							}
						}
					],
					"request": {
						"method": "GET",
						"header": [
							{
								"key": "Authorization",
								"value": "{{_token}}"
							}
						],
						"url": {
							"raw": "https://juice-shop.herokuapp.com/rest/products/search?q=",
							"protocol": "https",
							"host": [
								"juice-shop",
								"herokuapp",
								"com"
							],
							"path": [
								"rest",
								"products",
								"search"
							],
							"query": [
								{
									"key": "q",
									"value": ""
								}
							]
						}
					},
					"response": []
				},
				{
					"name": "AddFirstItemToBasket",
					"event": [
						{
							"listen": "prerequest",
							"script": {
								"exec": [
									""
								],
								"type": "text/javascript"
							}
						},
						{
							"listen": "test",
							"script": {
								"exec": [
									"/* test the add item to basket API",
									"    Author: Tony Nguyen",
									"    Issue date: Sat 21 May, 2022",
									"*/",
									"",
									"var jsonData = JSON.parse(responseBody);",
									"//Get and save the basket order id of the first product item",
									"pm.environment.set(\"_product_1_basket_id\", jsonData.data.id);",
									"",
									"//Successful to add the first item to basket with status code is 200",
									"pm.test(\"Successful to add the first item to basket with status code is 200\", function () {",
									"    pm.response.to.have.status(200);",
									"});"
								],
								"type": "text/javascript"
							}
						}
					],
					"request": {
						"method": "POST",
						"header": [
							{
								"key": "Authorization",
								"value": "{{_token}}"
							},
							{
								"key": "Content-Type",
								"value": "application/json; charset=utf-8",
								"type": "text"
							}
						],
						"body": {
							"mode": "raw",
							"raw": "{\"ProductId\":{{_product_1_id}},\"BasketId\":\"{{_bid}}\",\"quantity\":1}"
						},
						"url": {
							"raw": "https://juice-shop.herokuapp.com/api/BasketItems/",
							"protocol": "https",
							"host": [
								"juice-shop",
								"herokuapp",
								"com"
							],
							"path": [
								"api",
								"BasketItems",
								""
							]
						}
					},
					"response": []
				},
				{
					"name": "AddSecondItemToBasket",
					"event": [
						{
							"listen": "prerequest",
							"script": {
								"exec": [
									""
								],
								"type": "text/javascript"
							}
						},
						{
							"listen": "test",
							"script": {
								"exec": [
									"/* test the add item to basket API",
									"    Author: Tony Nguyen",
									"    Issue date: Sat 21 May, 2022",
									"*/",
									"",
									"//Successful to add the second item to basket with status code is 200",
									"pm.test(\"Successful to add the second item to basket with status code is 200\", function () {",
									"    pm.response.to.have.status(200);",
									"});"
								],
								"type": "text/javascript"
							}
						}
					],
					"request": {
						"method": "POST",
						"header": [
							{
								"key": "Authorization",
								"value": "{{_token}}"
							},
							{
								"key": "Content-Type",
								"value": "application/json; charset=utf-8",
								"type": "text"
							}
						],
						"body": {
							"mode": "raw",
							"raw": "{\"ProductId\":{{_product_2_id}},\"BasketId\":\"{{_bid}}\",\"quantity\":1}"
						},
						"url": {
							"raw": "https://juice-shop.herokuapp.com/api/BasketItems/",
							"protocol": "https",
							"host": [
								"juice-shop",
								"herokuapp",
								"com"
							],
							"path": [
								"api",
								"BasketItems",
								""
							]
						}
					},
					"response": []
				},
				{
					"name": "Verify2ItemsInBasket",
					"event": [
						{
							"listen": "prerequest",
							"script": {
								"exec": [
									""
								],
								"type": "text/javascript"
							}
						},
						{
							"listen": "test",
							"script": {
								"exec": [
									"/* Test the get basket item API",
									"    Author: Tony Nguyen",
									"    Issue date: Sat 21 May, 2022",
									"*/",
									"",
									"//Successful to get the basket item with status code is 200",
									"pm.test(\"Successful to get the basket item with status code is 200\", function () {",
									"    pm.response.to.have.status(200);",
									"});",
									"",
									"var jsonData = JSON.parse(responseBody);",
									"//Verify that the user basket have 2 items with the correct item name",
									"pm.test(\"Verify that the user basket have 2 items with the correct item name\", function () {",
									"    pm.expect(jsonData.data.Products.length).is.equal(2);",
									"});",
									"",
									"//Verify that the first item in basket has the correct item id and name",
									"pm.test(\"Verify that the first item in basket has the correct item id and name\", function () {",
									"    pm.expect(jsonData.data.Products[0].id).is.equal(Number(pm.environment.get(\"_product_1_id\")));",
									"    pm.expect(jsonData.data.Products[0].name).is.equal(pm.environment.get(\"_product_1_name\"));",
									"});",
									"",
									"//Verify that the second item in basket has the correct item id and name",
									"pm.test(\"Verify that the second item in basket has the correct item id and name\", function () {",
									"    pm.expect(jsonData.data.Products[1].id).is.equal(Number(pm.environment.get(\"_product_2_id\")));",
									"    pm.expect(jsonData.data.Products[1].name).is.equal(pm.environment.get(\"_product_2_name\"));",
									"});"
								],
								"type": "text/javascript"
							}
						}
					],
					"request": {
						"method": "GET",
						"header": [
							{
								"key": "Authorization",
								"value": "{{_token}}"
							}
						],
						"url": {
							"raw": "https://juice-shop.herokuapp.com/rest/basket/{{_bid}}",
							"protocol": "https",
							"host": [
								"juice-shop",
								"herokuapp",
								"com"
							],
							"path": [
								"rest",
								"basket",
								"{{_bid}}"
							]
						}
					},
					"response": []
				},
				{
					"name": "deleteItemFromBasket",
					"event": [
						{
							"listen": "test",
							"script": {
								"exec": [
									"/* Test the get basket item API",
									"    Author: Tony Nguyen",
									"    Issue date: Sat 21 May, 2022",
									"*/",
									"",
									"//Successful to get the basket item with status code is 200",
									"pm.test(\"Successful to get the basket item with status code is 200\", function () {",
									"    pm.response.to.have.status(200);",
									"});",
									"",
									"var jsonData = JSON.parse(responseBody);",
									"//Verify that the response status is success",
									"pm.test(\"Verify that the response status is success\", function () {",
									"    pm.expect(jsonData.status).is.equal(\"success\");",
									"});",
									""
								],
								"type": "text/javascript"
							}
						}
					],
					"request": {
						"method": "DELETE",
						"header": [
							{
								"key": "Authorization",
								"value": "{{_token}}"
							}
						],
						"url": {
							"raw": "https://juice-shop.herokuapp.com/api/BasketItems/{{_product_1_basket_id}}",
							"protocol": "https",
							"host": [
								"juice-shop",
								"herokuapp",
								"com"
							],
							"path": [
								"api",
								"BasketItems",
								"{{_product_1_basket_id}}"
							]
						}
					},
					"response": []
				},
				{
					"name": "Verify1ItemInBasket",
					"event": [
						{
							"listen": "prerequest",
							"script": {
								"exec": [
									""
								],
								"type": "text/javascript"
							}
						},
						{
							"listen": "test",
							"script": {
								"exec": [
									"/* Test the get basket item API",
									"    Author: Tony Nguyen",
									"    Issue date: Sat 21 May, 2022",
									"*/",
									"",
									"//Successful to get the basket item with status code is 200",
									"pm.test(\"Successful to get the basket item with status code is 200\", function () {",
									"    pm.response.to.have.status(200);",
									"});",
									"",
									"var jsonData = JSON.parse(responseBody);",
									"//Verify that the user basket have 2 items with the correct item name",
									"pm.test(\"Verify that the user basket have 2 items with the correct item name\", function () {",
									"    pm.expect(jsonData.data.Products.length).is.equal(1);",
									"});",
									"",
									"//Verify that the first item in basket has the correct item id and name",
									"// The _product_1_ item is delete already, so the only item now is _product_2_id",
									"pm.test(\"Verify that the first item in basket has the correct item id and name\", function () {",
									"    pm.expect(jsonData.data.Products[0].id).is.equal(Number(pm.environment.get(\"_product_2_id\")));",
									"    pm.expect(jsonData.data.Products[0].name).is.equal(pm.environment.get(\"_product_2_name\"));",
									"});"
								],
								"type": "text/javascript"
							}
						}
					],
					"request": {
						"method": "GET",
						"header": [
							{
								"key": "Authorization",
								"value": "{{_token}}"
							}
						],
						"url": {
							"raw": "https://juice-shop.herokuapp.com/rest/basket/{{_bid}}",
							"protocol": "https",
							"host": [
								"juice-shop",
								"herokuapp",
								"com"
							],
							"path": [
								"rest",
								"basket",
								"{{_bid}}"
							]
						}
					},
					"response": []
				}
			]
		}
	]
}
import 'package:flutter/material.dart';
import 'dart:math';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({ Key? key }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: "Prueba",
      home: Inico(),
    );
  }
}

class Inico extends StatefulWidget {
  const Inico({ Key? key }) : super(key: key);

  @override
  _InicoState createState() => _InicoState();
}

class _InicoState extends State<Inico> {

  String randomNumber = 'x';

  @override
  Widget build(BuildContext context) {
    return Scaffold(

      appBar: AppBar(
        title: Text("Aplicacion de prueba"),
      ),
      body: Container(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget> [
            Text("El numero generado es: $randomNumber",
            style: TextStyle(fontSize: 25.0)),

            Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: <Widget> [
                RaisedButton(
                  color: Colors.lightGreen,
                  textColor: Colors.white,
                  child: Text("Generar"),
                  onPressed: (){
                    setState(() {
                      randomNumber = Random().nextInt(101).toString();
                    });
                  },
                )
              ],
            )

          ],
        ),
      ),
    );
  }
}
package com.example.centros_personal_profesores;

import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	SQLiteDatabase db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button crear =(Button)findViewById(R.id.crear);
		Button centros =(Button)findViewById(R.id.centros);
		Button personal =(Button)findViewById(R.id.personal);
		Button profesores =(Button)findViewById(R.id.profesores);
		Button estadisticas =(Button)findViewById(R.id.estadisticas);

		crear.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				creaBD();
			}
		}); 
		
		centros.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent i=new Intent(MainActivity.this,VerCentros.class);
				startActivity(i);
				
			}
		});
		
		personal.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent i=new Intent(MainActivity.this,VerPersonal.class);
				startActivity(i);
				
			}
		});
		
		profesores.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent i=new Intent(MainActivity.this,VerProfesores.class);
				startActivity(i);
				
			}
		});
		
		estadisticas.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i=new Intent(MainActivity.this,Estadisticas.class);
				startActivity(i);
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void creaBD(){
		
		ClientesSQLiteHelper sqlite=new ClientesSQLiteHelper(this, "CENTROS-PERSONAL-PROFESORES", null, 1);
		db=sqlite.getWritableDatabase();
		db.execSQL("CREATE TABLE centros (" +
				" cod_centro   SMALLINT NOT NULL," +
				" tipo_centro  CHAR(1)," +
				"nombre       VARCHAR(30)," +
				"direccion    VARCHAR(26)," +
				"telefono     VARCHAR(10)," +
				"num_plazas   SMALLINT  UNSIGNED," +
				" PRIMARY KEY (cod_centro) ) ;");
		
		db.execSQL("INSERT INTO centros VALUES (10,'S','IES El Quijote','Avda. Los Molinos 25', '965-887654',538)");
		db.execSQL(	"INSERT INTO centros VALUES (15,'P','CP Los Danzantes', 'C/Las Musas s/n','985-112322',250)");
		db.execSQL("INSERT INTO centros VALUES (22,'S', 'IES Planeta Tierra', 'C/Mina 45','925-443400',300)");
		db.execSQL("INSERT INTO centros VALUES (45,'P', 'CP Manuel Hidalgo', 'C/Granada 5','926-202310',220)");
		db.execSQL("INSERT INTO centros VALUES (50,'S', 'IES Antoñete 1', 'C/Los Toreros 21','989-406090',310)");
		
		db.execSQL("CREATE TABLE personal (cod_centro   SMALLINT NOT NULL," +
				"dni          INT UNSIGNED NOT NULL," +
				"apellidos    VARCHAR(30)," +
				"funcion      VARCHAR(15)," +
				"salario      FLOAT(7,2)," +
				"PRIMARY KEY (dni)," +
				"FOREIGN KEY (cod_centro) REFERENCES centros (cod_centro));");
		
		db.execSQL("INSERT INTO personal VALUES (10,4480099, 'Ruano Cerezo, Manuel','ADMINISTRATIVO', 1800.00)");
		db.execSQL("INSERT INTO personal VALUES (15,1002345, 'Albarrán Serrano, Alicia', 'ADMINISTRATIVO', 1800.00)");
		db.execSQL("INSERT INTO personal VALUES (15,7002660, 'Munyoz Rey, Felicia', 'ADMINISTRATIVO', 1800.00)");
		db.execSQL("INSERT INTO personal VALUES (22,5502678, 'Marín Marn, Pedro', 'ADMINISTRATIVO', 1800.00)");
		db.execSQL("INSERT INTO personal VALUES (22,6600980, 'Peinado Gil, Elena','CONSERJE', 1750.00)");
		db.execSQL("INSERT INTO personal VALUES (45,4163222, 'Sarro Molina, Carmen','CONSERJE', 1750.00)");
		db.execSQL("INSERT INTO personal VALUES (10,1112345,'Martnez Salas, Fernando',  'PROFESOR',2200.00)");
		db.execSQL("INSERT INTO personal VALUES (10,4123005,'Bueno Zarco, Elisa', 'PROFESOR',2200.00)");
		db.execSQL("INSERT INTO personal VALUES (10,4122025,'Montes Garca, M.Pilar', 'PROFESOR',2200.00)");
		db.execSQL("INSERT INTO personal VALUES (15,9800990, 'Ramos Ruiz, Luis', 	'PROFESOR',2050.00)");
		db.execSQL("INSERT INTO personal VALUES (15,1112355,'Rivera Silvestre, Ana', 'PROFESOR',2050.00)");
		db.execSQL("INSERT INTO personal VALUES (15,8660990, 'De Lucas Fdez, M.Angel',  'PROFESOR',2050.00)");
		db.execSQL("INSERT INTO personal VALUES (22,7650000, 'Ruiz Lafuente, Manuel',  'PROFESOR',2200.00)");
		db.execSQL("INSERT INTO personal VALUES (45,43526789, 'Serrano Lagua, María','PROFESOR',2050.00);");
		
		db.execSQL("CREATE TABLE profesores (" +
				"cod_centro   SMALLINT NOT NULL," +
				"dni          INT UNSIGNED NOT NULL," +
				"apellidos    VARCHAR(30)," +
				"especialidad VARCHAR(16)," +
				"PRIMARY KEY (dni)," +
				"FOREIGN KEY (dni) REFERENCES personal(dni)," +
				"FOREIGN KEY (cod_centro) REFERENCES centros (cod_centro));");
		
		db.execSQL("INSERT INTO profesores VALUES (10,1112345,'Martínez Salas, Fernando',  'INFORMÁTICA')");
		db.execSQL("INSERT INTO profesores VALUES (10,4123005,'Bueno Zarco, Elisa', 'MATEMÁTICAS')");
		db.execSQL("INSERT INTO profesores VALUES (10,4122025,'Montes García, M.Pilar', 'MATEMÁTICAS')");
		db.execSQL("INSERT INTO profesores VALUES (15,9800990, 'Ramos Ruiz, Luis', 	'LENGUA')");
		db.execSQL("INSERT INTO profesores VALUES (15,1112355,'Rivera Silvestre, Ana', 'DIBUJO')");
		db.execSQL("INSERT INTO profesores VALUES (15,8660990, 'De Lucas Fdez, M.Angel',  'MATEMÁTICAS')");
		db.execSQL("INSERT INTO profesores VALUES (22,7650000, 'Ruiz Lafuente, Manuel',  'MATEMÁTICAS')");
		db.execSQL("INSERT INTO profesores VALUES (45,43526789, 'Serrano Laguía, María','INFORMÁTICA');");
		db.close();
				
	}
	

}

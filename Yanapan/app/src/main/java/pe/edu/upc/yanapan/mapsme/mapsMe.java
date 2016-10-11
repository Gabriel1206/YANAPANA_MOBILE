package pe.edu.upc.yanapan.mapsme;

/*public MWMPoint toMWMPoint(double mLat, double mLon, String mName, String mId){
	return new MWMPoint(mLat, mLon, mName, mId);
}

private void mostrarUsuariosEnMapa(List<Padron> lista)
{
	MWMPoint[] points = new MWMPoint[lista.size()];
	for (int i = 0; i < lista.size(); i++){
		
		String nombres = "Datos : " + lista.get(i).getDni() + " - " + lista.get(i).getPaterno() + " " + lista.get(i).getMaterno() + ", " + lista.get(i).getNombres() +
				"\nCondición : " + lista.get(i).getDescripcionCondicion() +
				"\nU.Visita : " + lista.get(i).getFechaVisita() +
				"\nDDirección : " + lista.get(i).getDdireccion();
		
		if(!lista.get(i).getLatitud().equals(""))
			points[i] = toMWMPoint(Double.parseDouble(lista.get(i).getLatitud().replace(",", ".")), Double.parseDouble(lista.get(i).getLongitud().replace(",", ".")), nombres, lista.get(i).getDni());
	}
    
	MapsWithMeApi.showPointsOnMap(this, "", ReporteUsuariosActivity.getPendingIntent(this), points);
}

mostrarUsuariosEnMapa(listaPadron);*/


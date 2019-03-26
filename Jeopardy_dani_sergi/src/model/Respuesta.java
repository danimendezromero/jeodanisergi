package model;

public class Respuesta {
    private String texto;
    private boolean respuestaCorrecta;

    public void setRespuestaCorrecta(boolean respuestaCorrecta) {
        this.respuestaCorrecta = respuestaCorrecta;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public boolean esRespuestaCorrecta() {
        return respuestaCorrecta;
    }

    @Override
    public String toString() {
        return "Respuesta{" + "texto='" + texto + '\'' + ", respuestaCorrecta=" + respuestaCorrecta + '}';
    }
}

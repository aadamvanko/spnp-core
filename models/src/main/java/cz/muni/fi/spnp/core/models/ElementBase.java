package cz.muni.fi.spnp.core.models;

public abstract class ElementBase {

    private Commentary commentary;

    public ElementBase() {
        commentary = new Commentary("");
    }

    public Commentary getCommentary() {
        return commentary;
    }

    public void setCommentary(String text) {
        this.commentary = new Commentary(text);
    }

    public void setCommentary(Commentary commentary) {
        this.commentary = commentary;
    }
}

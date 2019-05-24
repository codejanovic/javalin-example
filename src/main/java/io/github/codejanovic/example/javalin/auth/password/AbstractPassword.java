package io.github.codejanovic.example.javalin.auth.password;


import io.github.codejanovic.example.javalin.misc.Text;

public abstract class AbstractPassword implements Password {
    private final Text _hash;
    private final Text _salt;

    public AbstractPassword(String password, String salt) {
        this(new Text.CaseSensitive(password), new Text.CaseSensitive(salt));
    }

    public AbstractPassword(Text password, Text salt) {
        _hash = password;
        _salt = salt;
    }

    @Override
    public Text salt() {
        return _salt;
    }

    @Override
    public Text hash() {
        return _hash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Password)) return false;

        Password that = (Password) o;

        return hash().equals(that.hash());
    }

    @Override
    public int hashCode() {
        return hash() != null ? hash().hashCode() : 0;
    }

}

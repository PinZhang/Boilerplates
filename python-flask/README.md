# Flask boilerplate

This boilerplate is for Flask projects.

# Install requirements

```sh
pip install -r requirements.txt
```

## MacOS

```sh
brew install python
pip install -r requirements.txt
```

# Start service

```sh
cp .env.example .env
python main.py
```

# Run tests

```sh
cp .env.example .env
make test
```

we could run with a single test file:

```sh
python -m unittest -p test_unit.py
```

# use vritualenv wrapper

```sh
pip install virtualenvwrapper
source $VIRTUALENV_DIR/virtualenvwrapper.sh
mkvirtualenv Bumblebee
workon Bumblebee
```

## source virtualenvwrapper.sh on MacOS (brew)

```sh
source /usr/local/bin/virtualenvwrapper.sh
```

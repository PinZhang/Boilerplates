# Glutton

Glutton is a data collector, the only job it does is saving data posted via http request into some place as the data is without any processing, the place to save the data could be configured, like rotated file, database, queue broker etc.

# POST /submit

This is the only endpoint to accept data.

# Install pre-commit hook

```sh
cd .git/hooks
ln -s ../../git-hooks/pre-commit ./pre-commit
```


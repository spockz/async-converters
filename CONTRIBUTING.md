# How to Contribute

I would really love to incorporate your contribution. Contribution could
be any of the following forms: feature requests or bug reports through 
[issues](https://github.com/spockz/async-converters/issues), pull 
requests for documentation improvement, or pull requests with actual
features or bug fixes.

## Workflow

This workflow probably works best for getting your contribution 
incorporated.

1.  Fork async-converters
2.  Check out the `develop` branch
3.  Make a feature branch (use `git checkout -b "cool-new-feature"`)
4.  Write a test for your change on your branch
5.  Make your cool new feature or bugfix on your branch
6.  From your branch, make a pull request against `spockz/async-converters/develop`
7.  Work with repo maintainer(s) to get your change reviewed
8.  Wait for your change to be pulled into `spockz/async-converters/develop`
9.  Merge `spockz/async-converters/develop` into your origin `develop`
10.  Delete your feature branch

## Testing

Currently, there is no default format for writing tests. However, the
[`FlatSpec` mixin][flatspec] is used in most tests so it probably
easiest to use that style. I recommend the use of `Matchers` as they
make the tests more declarative.

## Style

We would really much appreciate if any new feature that is added also 
has a test for Java compatibility, at the very least to check whether 
the code is callable from Java without having to use any identifiers 
with `$` in them.

We generally follow [Effective Scala][es] and the [Scala Style Guide][ssg]. When
in doubt, look around the codebase and see how it's done elsewhere.

Comments should be formatted to a width no greater than 80 columns.

Files should be exempt of trailing spaces.

We adhere to a specific format for commit messages. Please write your commit
messages along these guidelines. Please keep the line width no greater than
80 columns (You can use `fmt -n -p -w 80` to accomplish this).

    One line description of your change (less than 72 characters)

    Problem

    Explain the context and why you're making that change.  What is the
    problem you're trying to solve? In some cases there is not a problem
    and this can be thought of being the motivation for your change.

    Solution

    Describe the modifications you've done.

    Result

    What will change as a result of your pull request? Note that sometimes
    this section is unnecessary because it is self-explanatory based on
    the solution.

## Code Review

Before a pull request gets merged it will first pass review. When the
pull-request is accepted the changes will be squashed into a single
commit and authorship of the commit will be preserved.

Please let us know if you have any questions about this process!

## Getting Started

Please get started by reporting any suggestions for improvement in the
issues section and also feel free to ask questions on 
[gitter](https://gitter.im/spockz/async-converters).

## Documentation

Please note that any additions or changes to the API must be thoroughly
described in [ScalaDoc][8] comments. We will also happily consider pull
requests that improve the existing ScalaDocs!


[3]: https://twitter.github.io/finagle/
[4]: http://sphinx-doc.org/
[5]: https://github.com/sbt/sbt-site
[6]: http://sphinx-doc.org/install.html
[7]: http://docutils.sourceforge.net/rst.html
[8]: http://docs.scala-lang.org/style/scaladoc.html
[9]: https://github.com/twitter/finagle/blob/master/finagle-core/src/test/java/com/twitter/finagle/AddrCompilationTest.java
[es]: https://twitter.github.io/effectivescala/
[funsuite]: http://www.scalatest.org/getting_started_with_fun_suite
[ostrich]: https://github.com/twitter/ostrich
[scalatest]: http://www.scalatest.org/
[ssg]: http://docs.scala-lang.org/style/scaladoc.html
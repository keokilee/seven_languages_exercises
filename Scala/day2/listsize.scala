def totalSize(strings: List[String]): Number = {
  return (0 /: strings) {(sum, str) => sum + str.size}
}

val list = List("foo", "bar", "baz")
println("Created list '" + list + "'")
println("The size of all the strings is " + totalSize(list))
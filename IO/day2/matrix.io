f := File with("matrix.txt")
contents := f openForReading contents

# List to hold the matrix
the_list := list()

# Split the contents and append them to the list.
contents split("\n") foreach(line,
  the_list append(line split())
)

f close

# Write to the output file.
f := File with("matrix2.txt") openForUpdating

# Join the contents of each row.
the_list foreach(row,
  f write(row join(" "))
  f write("\n")
)

f close
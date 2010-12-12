max_random = ARGV[0].to_i
max_random = 10 if max_random.nil?
random_num = rand(max_random) + 1

while true do
  puts "Pick a number from 1 to #{max_random}"
  input = STDIN.gets.to_i
  if not input
    puts "Invalid input"
  elsif input > max_random
    puts "Too high"
  elsif input > random_num
    puts "Lower"
  elsif input == random_num
    puts "Correct!"
    break
  elsif input < 1
    puts "Too low"
  elsif input < random_num
    puts "Higher"
  end
end
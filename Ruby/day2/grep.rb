if ARGV.size != 2
  puts "Usage: ruby grep.rb <file> <phrase>"
  exit(1)
end

unless File.exist?(ARGV[0])
  puts "Could not find file #{ARGV[0]}"
  exit(1)
end

File.open(ARGV[0]) do |f|
  f.readlines.each_with_index do |l, i|
    if l.match ARGV[1]
      puts "#{i} #{l}"
    end
  end
end
    
  
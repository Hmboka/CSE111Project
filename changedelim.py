import csv

reader = csv.reader(open("testbef.csv", "rU"), delimiter=',')
writer = csv.writer(open("favebooks.tbl", 'w'), delimiter='|')
writer.writerows(reader)

print("Delimiter successfully changed")
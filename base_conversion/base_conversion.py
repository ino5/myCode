print()
BASE = int(input("Base(radix): "))
N_FRACTION = int(input("Places of Decimals: "))
INPUT = input("Decimal number less than 1: ")
print()
underzero_value = str(INPUT)
underzero_value_list = list(underzero_value)
del underzero_value_list[0]
del underzero_value_list[0]
underzero_check_powerOfTen = int(len(underzero_value_list))
underzero_check_hurdle = 10**underzero_check_powerOfTen
underzero_value_test = int("".join(map(str, underzero_value_list)))
underzero_answer_list = []


# conversion the underzero part
while True:
    underzero_value_test *= BASE
    if underzero_value_test >= underzero_check_hurdle:
        underzero_answer_list_integerPart = underzero_value_test//underzero_check_hurdle
        underzero_answer_list.append(underzero_answer_list_integerPart)
        underzero_value_test -= underzero_answer_list_integerPart*underzero_check_hurdle
    else:
        underzero_answer_list.append(0)
    if len(underzero_answer_list) == N_FRACTION:
        break


print(f'Base-{BASE}: 0.',end="")

# print the underzero part (base-N)
for i in range(len(underzero_answer_list)):
    if underzero_answer_list[i]<10:
        print(underzero_answer_list[i],end="")
    elif 10<=underzero_answer_list[i]<=35:
        print(f'{chr(underzero_answer_list[i]+55)}', end="")
    else:
        print(f'\'{underzero_answer_list[i]}\'', end="")

    if (i+1)%5 == 0:
        print(" ",end="")
print()



print()
print(f'Decimal number from the Base-{BASE}: ')

# print the underzero part (fraction of decimal number from the base-N)
n_first_print = 1
for i in range(len(underzero_answer_list)):
    if underzero_answer_list[i] > 0:
        if n_first_print == 1:
            print("%d/%d^%d" % (underzero_answer_list[i], BASE, (i+1)), end="")
            n_first_print = 0
        else:
            print(" + %d/%d^%d" % (underzero_answer_list[i], BASE, (i+1)), end="")
print()
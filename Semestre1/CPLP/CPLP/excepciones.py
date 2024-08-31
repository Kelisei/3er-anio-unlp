suspicious_function = lambda: None

class MyException(Exception):
    pass

try:
    for i in range(10):
        try:
            suspicious_function()
        except ZeroDivisionError:
            print("Error: Division by zero")
            raise Exception("Error: Division by zero")
        except FileNotFoundError:
            print("Error: File not found")
        except IndexError:
            print("Error: Index out of range")
        except ValueError:
            print("Error: Invalid value")
        finally:
            print("This code will always run")
except Exception as e:
    print(f"An error occurred: {e}")
else:
    print("No exceptions were raised")

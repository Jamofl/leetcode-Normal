def bar(path, flag):
    if flag:
        path.append(1111)
        path.append(2222)
    else:
        # path.pop()
        path = path[: -1]
        bar(path, True)


def foo(path, flag=False):
    if flag:
        path += 'z'
        path += 'z'
    else:
        path = path[:-1]
        foo(path, True)


def zar(ss):
    ss = "a"


if __name__ == '__main__':
    p = [1, 2, 3, 4, 5, 6, 7]
    bar(p, False)
    print(p)

    s = "abc"
    foo(s)
    print(s)

    s = "abc"
    zar(s)
    print("by Value" if s == "a" else "by Reference")

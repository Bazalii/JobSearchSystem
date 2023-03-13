package commonClasses

interface IThrowingValidator<T> {
    fun validate(checkedObject: T)
}
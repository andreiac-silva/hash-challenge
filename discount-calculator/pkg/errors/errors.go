package errors

type DBClientCreationError struct {
	Err error
}

func (e *DBClientCreationError) Error() string {
	if e.Err != nil {
		return e.Err.Error()
	}
	return "Error creating mongo client."
}

type DatabaseConnectionError struct {
	Err error
}

func (e *DatabaseConnectionError) Error() string {
	if e.Err != nil {
		return e.Err.Error()
	}
	return "Something wrong with database connection."
}

type EntityNotFound struct {
	Err     error
	Message string
}

func (e *EntityNotFound) Error() string {
	return e.Message
}

type InvalidAttribute struct {
	Err     error
	Message string
}

func (e *InvalidAttribute) Error() string {
	return e.Message
}

type MissingAttribute struct {
	Message string
}

func (e *MissingAttribute) Error() string {
	return e.Message
}

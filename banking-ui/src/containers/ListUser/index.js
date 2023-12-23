import React, { useEffect, useState } from 'react';
import Table from '../../components/Table';
import { useNavigate } from 'react-router-dom';

const ListUsers = () => {
  const navigate = useNavigate();

  const [users, setusers] = useState(null);
  const [selectedUser, setSelectedUser] = useState(null);
  const [amount, setAmount] = useState(0);
  const [installmentCount, setInstallmentCOunt] = useState(0);

  useEffect(() => {
    listUsers();
  }, []);

  const listUsers = async () => {
    const response = await fetch('http://localhost:8080/users/list');
    const users = await response.json();
    setusers(users);
  };

  const handleListCreditsClick = item => {
    navigate(`/listCredits/${item.id}`);
  };

  const handleAddCreditClick = user => {
    setSelectedUser(user);
  };

  const handleCreateCredit = e => {
    e.preventDefault();
    const body = {
      userId: selectedUser.id,
      amount,
      installmentCount,
    };
    createCredit(body);
  };

  const createCredit = async body => {
    await fetch('http://localhost:8080/credits/add', {
      method: 'POST',
      headers: {
        Accept: 'application/json',
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(body),
    });
    navigate(`/listCredits/${selectedUser.id}`);
  };

  const columns = [
    {
      title: 'Id',
      field: 'id',
    },
    {
      title: 'First Name',
      field: 'firstname',
    },
    {
      title: 'Last Name',
      field: 'lastname',
    },
    {
      title: 'Actions',
      field: item => (
        <>
          <button
            data-bs-toggle='modal'
            data-bs-target='#creditModal'
            type='button'
            className='btn btn-primary me-3'
            onClick={() => handleAddCreditClick(item)}
          >
            Add Credits
          </button>
          <button
            type='button'
            className='btn btn-primary'
            onClick={() => handleListCreditsClick(item)}
          >
            List Credits
          </button>
        </>
      ),
    },
  ];

  return (
    <>
      <Table columns={columns} data={users} />
      <div
        class='modal fade'
        id='creditModal'
        tabindex='-1'
        aria-labelledby='creditModal'
        aria-hidden='true'
      >
        <div class='modal-dialog'>
          <div class='modal-content'>
            <div class='modal-header'>
              <h1 class='modal-title fs-5' id='creditModal'>
                Pay Installments
              </h1>
              <button
                type='button'
                class='btn-close'
                data-bs-dismiss='modal'
                aria-label='Close'
              ></button>
            </div>
            <div class='modal-body'>
              <form>
                <div className='mb-3'>
                  <label htmlFor='userId' className='form-label'>
                    User Id
                  </label>
                  <input
                    disabled
                    className='form-control'
                    id='userId'
                    value={selectedUser && selectedUser.id}
                  />
                </div>
                <div className='mb-3'>
                  <label htmlFor='amount' className='form-label'>
                    Amount
                  </label>
                  <input
                    onChange={e => setAmount(e.target.value)}
                    className='form-control'
                    id='amount'
                    value={amount}
                    placeholder='Enter your amount'
                  />
                </div>
                <div className='mb-3'>
                  <label htmlFor='installmentCount' className='form-label'>
                    Installment Count
                  </label>
                  <input
                    onChange={e => setInstallmentCOunt(e.target.value)}
                    className='form-control'
                    id='installmentCount'
                    value={installmentCount}
                    placeholder='Enter your installment count'
                  />
                </div>
              </form>
            </div>
            <div class='modal-footer'>
              <button
                type='button'
                class='btn btn-secondary'
                data-bs-dismiss='modal'
              >
                Cancel
              </button>
              <button
                type='button'
                class='btn btn-primary'
                onClick={handleCreateCredit}
                data-bs-dismiss='modal'
              >
                Save
              </button>
            </div>
          </div>
        </div>
      </div>
    </>
  );
};

export default ListUsers;

import React, { useEffect, useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import Table from '../../components/Table';

const ListCreditInstallments = () => {
  let { creditId } = useParams();
  const navigate = useNavigate();

  const [installments, setInstallments] = useState(null);
  const [amount, setAmount] = useState(0);
  const [selectedInstallment, setSelectedInstallment] = useState(null);

  useEffect(() => {
    listCreditInstallments(creditId);
  }, [creditId]);

  const listCreditInstallments = async creditId => {
    const response = await fetch(
      `http://localhost:8080/installments/list/${creditId}`,
    );
    const installments = await response.json();
    setInstallments(installments);
  };

  const handlePayClick = item => {
    setSelectedInstallment(item);
  };

  const handlePay = e => {
    e.preventDefault();
    const body = {
      installmentId: selectedInstallment.id,
      amount,
    };
    payInstallment(body);
  };

  const payInstallment = async body => {
    const response = await fetch('http://localhost:8080/installments/pay', {
      method: 'POST',
      headers: {
        Accept: 'application/json',
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(body),
    });
    navigate(0);
  };

  const columns = [
    {
      title: 'Id',
      field: 'id',
    },
    {
      title: 'Due Date',
      field: 'dueDate',
    },
    {
      title: 'Remaining Amount',
      field: 'remainingAmount',
    },
    {
      title: 'Total Amount',
      field: 'totalAmount',
    },
    {
      title: 'Status',
      field: 'status',
    },
    {
      title: 'Actions',
      field: item => (
        <button
          data-bs-toggle='modal'
          data-bs-target='#payModal'
          type='button'
          className='btn btn-primary'
          onClick={() => handlePayClick(item)}
        >
          Pay
        </button>
      ),
    },
  ];

  return (
    <>
      <div
        class='modal fade'
        id='payModal'
        tabindex='-1'
        aria-labelledby='payModal'
        aria-hidden='true'
      >
        <div class='modal-dialog'>
          <div class='modal-content'>
            <div class='modal-header'>
              <h1 class='modal-title fs-5' id='payModal'>
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
                  <label htmlFor='name' className='form-label'>
                    Id
                  </label>
                  <input
                    disabled
                    className='form-control'
                    id='amount'
                    value={selectedInstallment && selectedInstallment.id}
                  />
                </div>
                <div className='mb-3'>
                  <label htmlFor='name' className='form-label'>
                    Amount
                  </label>
                  <input
                    onChange={e => setAmount(e.target.value)}
                    className='form-control'
                    id='amount'
                    value={amount}
                    placeholder='Enter your name'
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
                onClick={handlePay}
                data-bs-dismiss='modal'
              >
                Save
              </button>
            </div>
          </div>
        </div>
      </div>
      <Table columns={columns} data={installments} />
    </>
  );
};

export default ListCreditInstallments;
